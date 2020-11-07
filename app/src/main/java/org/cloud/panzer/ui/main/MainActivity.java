package org.cloud.panzer.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.base.BaseFragment;
import org.cloud.core.rx.Subscribe;
import org.cloud.core.utils.LogUtils;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.panzer.R;

import butterknife.BindView;

import static org.cloud.core.rx.RxBusCode.RX_BUS_CODE_MAIN_SEARCH_SHOW;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    private HomeFragment mHomeFragment;
    private CateFragment mCateFragment;
    private SearchFragment mSearchFragment;
    private CartFragment mCartFragment;
    private MineFragment mMineFragment;

    // 默认为0;
    private int mCurrIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            LogUtils.d("onRestore enter...." + mCurrIndex);
            mCurrIndex = savedInstanceState.getInt("currTabIndex");
        }
        switchFragment(mCurrIndex);
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    protected void initView() {
        mBottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mBottomNavigationView.setItemTextAppearanceActive(R.style.bottom_selected_text);
        mBottomNavigationView.setItemTextAppearanceInactive(R.style.bottom_normal_text);
        StatusBarUtils.setStatusBarMode(this, false, R.color.primary);
    }

    @Override
    protected void initListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.tab_home) {
                switchFragment(0);
            } else if (id == R.id.tab_cate) {
                switchFragment(1);
            } else if (id == R.id.tab_search) {
                switchFragment(2);
            } else if (id == R.id.tab_cart) {
                switchFragment(3);
            } else if (id == R.id.tab_mine) {
                switchFragment(4);
            }
            return true;
        });
    }

    @Override
    protected void initData() {
    }

    // 自定义数据和方法

    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mCateFragment) {
            transaction.hide(mCateFragment);
        }
        if (null != mSearchFragment) {
            transaction.hide(mSearchFragment);
        }
        if (null != mCartFragment) {
            transaction.hide(mCartFragment);
        }
        if (null != mMineFragment) {
            transaction.hide(mMineFragment);
        }
    }

    public void switchFragment(int position) {
        // Fragment事务管理器
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        //LogUtils.d("current position tab" + position);
        switch (position) {
            //首页
            case 0: //视频
                if (mHomeFragment == null) {
                    mHomeFragment = BaseFragment.newInstance(HomeFragment.class, new Bundle());
                    transaction.add(R.id.fl_content, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;

            case 1: //更多
                if (mCateFragment == null) {
                    mCateFragment = BaseFragment.newInstance(CateFragment.class, new Bundle());
                    transaction.add(R.id.fl_content, mCateFragment, "cate");
                } else {
                    transaction.show(mCateFragment);
                }
                break;

            case 2: //更多
                if (mSearchFragment == null) {
                    mSearchFragment = BaseFragment.newInstance(SearchFragment.class, new Bundle());
                    transaction.add(R.id.fl_content, mSearchFragment, "search");
                } else {
                    transaction.show(mSearchFragment);
                }
                break;

            case 3: //更多
                if (mCartFragment == null) {
                    mCartFragment = BaseFragment.newInstance(CartFragment.class, new Bundle());
                    transaction.add(R.id.fl_content, mCartFragment, "cart");
                } else {
                    transaction.show(mCartFragment);
                }
                break;

            default:
                if (mMineFragment == null) {
                    mMineFragment = BaseFragment.newInstance(MineFragment.class, new Bundle());
                    transaction.add(R.id.fl_content, mMineFragment, "mine");
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Subscribe(code = RX_BUS_CODE_MAIN_SEARCH_SHOW)
    public void rxBusEvent() {
        switchFragment(2);
    }
}