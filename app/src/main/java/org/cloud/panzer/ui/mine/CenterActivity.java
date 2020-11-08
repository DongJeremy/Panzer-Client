package org.cloud.panzer.ui.mine;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseShared;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.CenterContract;
import org.cloud.panzer.mvp.presenter.CenterPresenter;

import butterknife.BindView;

public class CenterActivity extends BaseMvpActivity<CenterPresenter> implements CenterContract.View {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;

    @BindView(R.id.logoutTextView)
    AppCompatTextView logoutTextView;

    @Override
    protected CenterPresenter createPresenter() {
        return new CenterPresenter();
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_center;
    }

    @Override
    protected void initView() {
        setToolbar(mainToolbar, "个人设置", R.color.whiteSub);
    }

    @Override
    protected void initListener() {
        logoutTextView.setOnClickListener(view -> logout());
    }

    @Override
    protected void initData() {

    }

    // 自定义数据和方法

    private void logout() {
        BaseToast.getInstance().show("注销成功！");
        App.getInstance().setNormalMember(true);
        BaseShared.getInstance().putString(BaseConstant.SHARED_C_LEVEL_HHR, "2");
        BaseShared.getInstance().putString(BaseConstant.SHARED_C_LEVEL_TYD, "2");
        BaseShared.getInstance().putString(BaseConstant.SHARED_B_LEVEL_TYD, "2");
        BaseShared.getInstance().putString(BaseConstant.SHARED_A_LEVEL_TYD, "2");
        BaseShared.getInstance().putString(BaseConstant.SHARED_KEY, "");
        App.getInstance().startMain(getActivity());
        App.getInstance().finish(getActivity());
    }
}