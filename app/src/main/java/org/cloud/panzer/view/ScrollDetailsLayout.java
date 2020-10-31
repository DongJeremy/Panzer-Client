package org.cloud.panzer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import org.cloud.panzer.R;

public class ScrollDetailsLayout extends LinearLayout {

    private static final float DEFAULT_PERCENT = 0.3f;
    private static final int DEFAULT_DURATION = 300;
    private int mMaxFlingVelocity;
    private int mMiniFlingVelocity;
    private int mDefaultPanel = 0;
    private int mDuration = DEFAULT_DURATION;
    private float mTouchSlop;
    private float mDownMotionY;
    private float mDownMotionX;
    private float mInitialInterceptY;
    private float mPercent = DEFAULT_PERCENT;

    public interface OnSlideFinishListener {
        void onStatueChanged(CurrentTargetIndex status);
    }

    public enum CurrentTargetIndex {
        UPSTAIRS,
        DOWNSTAIRS;

        public static CurrentTargetIndex valueOf(int i) {
            return 1 == i ? DOWNSTAIRS : UPSTAIRS;
        }
    }

    public void setPercent(float percent) {
        mPercent = percent;
    }

    /**
     * flag for listview or scrollview ,if child overscrolled ,do not judge view region 滚过头了，还是可以滚动
     */
    private boolean mChildHasScrolled;

    private View mUpstairsView;
    private View mDownstairsView;
    private View mCurrentTargetView;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;
    private OnSlideFinishListener mOnSlideDetailsListener;
    private CurrentTargetIndex mCurrentViewIndex = CurrentTargetIndex.UPSTAIRS;

    public ScrollDetailsLayout(Context context) {
        this(context, null);
    }

    public ScrollDetailsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollDetailsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mDefaultPanel = 0;
        this.mDuration = DEFAULT_DURATION;
        this.mPercent = DEFAULT_PERCENT;
        this.mCurrentViewIndex = CurrentTargetIndex.UPSTAIRS;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScrollDetailsLayout, defStyleAttr, 0);
        mPercent = a.getFloat(R.styleable.ScrollDetailsLayout_percent, DEFAULT_PERCENT);
        mDuration = a.getInt(R.styleable.ScrollDetailsLayout_duration, DEFAULT_DURATION);
        mDefaultPanel = a.getInt(R.styleable.ScrollDetailsLayout_default_panel, 0);
        a.recycle();
        mScroller = new Scroller(getContext(), new DecelerateInterpolator());
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mMaxFlingVelocity = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
        mMiniFlingVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        setOrientation(VERTICAL);
    }

    public void setOnSlideDetailsListener(OnSlideFinishListener onSlideFinishListener) {
        this.mOnSlideDetailsListener = onSlideFinishListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childCount = getChildCount();
        if (1 >= childCount) {
            throw new RuntimeException("SlideDetailsLayout only accept childs more than 1!!");
        }
        mUpstairsView = getChildAt(0);
        mDownstairsView = getChildAt(1);
    }

    /**
     * requestDisallowInterceptTouchEvent guarantee DragScrollDetailsLayout intercept event as wish
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * intercept rules：
     * 1. The vertical displacement is larger than the horizontal displacement;
     * 2. Panel stauts is UPSTAIRS：  slide up
     * 3. Panel status is DOWNSTAIRS：slide down
     * 4. child can requestDisallowInterceptTouchEvent();
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        if (actionMasked == 0) {
            resetDownPosition(ev);
            return false;
        } else if (actionMasked != 2) {
            return false;
        } else {
            adjustValidDownPoint(ev);
            return checkCanInterceptTouchEvent(ev);
        }
    }

    private void resetDownPosition(MotionEvent motionEvent) {
        this.mDownMotionX = motionEvent.getX();
        this.mDownMotionY = motionEvent.getY();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.clear();
        this.mChildHasScrolled = false;
        this.mInitialInterceptY = (float) ((int) motionEvent.getY());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                flingToFinishScroll();
                recycleVelocityTracker();
                break;
            case MotionEvent.ACTION_MOVE:
                scroll(ev);
                break;
            default:
                break;
        }
        return true;
    }

    private boolean checkCanInterceptTouchEvent(MotionEvent ev) {
        final float xDiff = ev.getX() - mDownMotionX;
        final float yDiff = ev.getY() - mDownMotionY;
        if (!canChildScrollVertically((int) yDiff, ev)) {
            mInitialInterceptY = (int) ev.getY();
            return Math.abs(yDiff) > mTouchSlop && Math.abs(yDiff) >= Math.abs(xDiff)
                    && !(mCurrentViewIndex == CurrentTargetIndex.UPSTAIRS && yDiff > 0
                    || mCurrentViewIndex == CurrentTargetIndex.DOWNSTAIRS && yDiff < 0);
        }
        return false;
    }

    private void adjustValidDownPoint(MotionEvent event) {
        if (mCurrentViewIndex == CurrentTargetIndex.UPSTAIRS && event.getY() > mDownMotionY
                || mCurrentViewIndex == CurrentTargetIndex.DOWNSTAIRS && event.getY() < mDownMotionY) {
            mDownMotionX = event.getX();
            mDownMotionY = event.getY();
        }
    }

    private void scroll(MotionEvent event) {
        if (mCurrentViewIndex == CurrentTargetIndex.UPSTAIRS) {
            if (getScrollY() <= 0 && event.getY() > mInitialInterceptY) {
                mInitialInterceptY = (int) event.getY();
            }
            scrollTo(0, (int) (mInitialInterceptY - event.getY()));
        } else {
            if (getScrollY() >= mUpstairsView.getMeasuredHeight() && event.getY() < mInitialInterceptY) {
                mInitialInterceptY = (int) event.getY();
            }
            scrollTo(0, (int) (mInitialInterceptY - event.getY() + mUpstairsView.getMeasuredHeight()));
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * if speed is enough even though offset is not enough go
     */

    private void flingToFinishScroll() {

        final int pHeight = mUpstairsView.getMeasuredHeight();
        final int threshold = (int) (pHeight * mPercent);
        float scrollY = getScrollY();
        if (CurrentTargetIndex.UPSTAIRS == mCurrentViewIndex) {
            if (scrollY <= 0) {
                scrollY = 0;
            } else if (scrollY <= threshold) {
                if (needFlingToToggleView()) {
                    scrollY = pHeight - getScrollY();
                    mCurrentViewIndex = CurrentTargetIndex.DOWNSTAIRS;
                } else
                    scrollY = -getScrollY();
            } else {
                scrollY = pHeight - getScrollY();
                mCurrentViewIndex = CurrentTargetIndex.DOWNSTAIRS;
            }
        } else if (CurrentTargetIndex.DOWNSTAIRS == mCurrentViewIndex) {
            if (pHeight - scrollY <= threshold) {
                if (needFlingToToggleView()) {
                    scrollY = -getScrollY();
                    mCurrentViewIndex = CurrentTargetIndex.UPSTAIRS;
                } else
                    scrollY = pHeight - scrollY;
            } else if (scrollY < pHeight) {
                scrollY = -getScrollY();
                mCurrentViewIndex = CurrentTargetIndex.UPSTAIRS;
            }
        }
        mScroller.startScroll(0, getScrollY(), 0, (int) scrollY, mDuration);
        if (mOnSlideDetailsListener != null) {
            mOnSlideDetailsListener.onStatueChanged(mCurrentViewIndex);
        }
        postInvalidate();
    }


    private boolean needFlingToToggleView() {
        mVelocityTracker.computeCurrentVelocity(1000, mMaxFlingVelocity);
        if (mCurrentViewIndex == CurrentTargetIndex.UPSTAIRS) {
            return -mVelocityTracker.getYVelocity() > mMiniFlingVelocity;
        } else {
            return mVelocityTracker.getYVelocity() > mMiniFlingVelocity;
        }
    }

    private View getCurrentTargetView() {
        return mCurrentViewIndex == CurrentTargetIndex.UPSTAIRS ? mUpstairsView : mDownstairsView;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    /***
     * 复用已经实现的View，省却了测量布局之类的麻烦
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    protected boolean canChildScrollVertically(int offSet, MotionEvent ev) {
        mCurrentTargetView = getCurrentTargetView();
        return canScrollVertically(mCurrentTargetView, -offSet, ev);
    }

    /***
     * judge is event  is in current view
     * 判断MotionEvent是否处于View上面
     */
    protected boolean isTransformedTouchPointInView(MotionEvent ev, View view) {
        float x = ev.getRawX();
        float y = ev.getRawY();
        int[] rect = new int[2];
        view.getLocationInWindow(rect);
        float localX = x - rect[0];
        float localY = y - rect[1];
        return localX >= 0 && localX < (view.getRight() - view.getLeft())
                && localY >= 0 && localY < (view.getBottom() - view.getTop());
    }

    /***
     * first    can view self  ScrollVertically
     * seconde  if View is ViewPager only judge current page
     * third    if view is viewgroup check it`s children
     */
    private boolean canScrollVertically(View view, int offSet, MotionEvent ev) {

        if (!mChildHasScrolled && !isTransformedTouchPointInView(ev, view)) {
            return false;
        }
        if (ViewCompat.canScrollVertically(view, offSet)) {
            mChildHasScrolled = true;
            return true;
        }
        if (view instanceof ViewPager) {
            return canViewPagerScrollVertically((ViewPager) view, offSet, ev);
        }
        if (view instanceof ViewGroup) {
            ViewGroup vGroup = (ViewGroup) view;
            for (int i = 0; i < vGroup.getChildCount(); i++) {
                if (canScrollVertically(vGroup.getChildAt(i), offSet, ev)) {
                    mChildHasScrolled = true;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canViewPagerScrollVertically(ViewPager viewPager, int offset, MotionEvent ev) {
        View primaryItem;
        return viewPager.getAdapter() instanceof DetailPagerAdapter
                && (primaryItem = ((DetailPagerAdapter) viewPager.getAdapter()).getPrimaryItem()) != null
                && canScrollVertically(primaryItem, offset, ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void scrollToTop() {
        if (this.mCurrentViewIndex == CurrentTargetIndex.DOWNSTAIRS) {
            this.mScroller.startScroll(0, getScrollY(), 0, -getScrollY(), this.mDuration);
            this.mCurrentViewIndex = CurrentTargetIndex.UPSTAIRS;
            postInvalidate();
        }
        OnSlideFinishListener onSlideFinishListener = this.mOnSlideDetailsListener;
        if (onSlideFinishListener != null) {
            onSlideFinishListener.onStatueChanged(this.mCurrentViewIndex);
        }
    }

}
