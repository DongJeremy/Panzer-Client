package org.cloud.core.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BaseDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public final int[] ATRRS = {16843284};
    private Context context;
    private final Drawable divider;
    private int orientation;
    public BaseDecoration(Context context2, int i) {
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(this.ATRRS);
        this.divider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        setOrientation(i);
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation");
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.orientation == 0) {
            drawVerticalLine(canvas, recyclerView, state);
        } else {
            drawHorizontalLine(canvas, recyclerView, state);
        }
    }

    public void drawHorizontalLine(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int bottom = childAt.getBottom() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            this.divider.setBounds(paddingLeft, bottom, width, this.divider.getIntrinsicHeight() + bottom);
            this.divider.draw(canvas);
        }
    }

    public void drawVerticalLine(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int paddingTop = recyclerView.getPaddingTop();
        int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            int right = childAt.getRight() + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            this.divider.setBounds(right, paddingTop, this.divider.getIntrinsicWidth() + right, height);
            this.divider.draw(canvas);
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.orientation == 0) {
            rect.set(0, 0, 0, 1);
        } else {
            rect.set(0, 0, 1, 0);
        }
    }

}
