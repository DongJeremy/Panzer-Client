package org.cloud.panzer.ui.common;

import android.content.Intent;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseShared;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.panzer.R;
import org.cloud.panzer.ui.main.MainActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.usernameEditText)
    AppCompatEditText usernameEditText;
    @BindView(R.id.passwordEditText)
    AppCompatEditText passwordEditText;
    @BindView(R.id.registerTextView)
    AppCompatTextView registerTextView;
    @BindView(R.id.findPassTextView)
    AppCompatTextView findPassTextView;
    @BindView(R.id.loginTextView)
    AppCompatTextView loginTextView;
    @BindView(R.id.qqImageView)
    AppCompatImageView qqImageView;
    @BindView(R.id.wxImageView)
    AppCompatImageView wxImageView;
    @BindView(R.id.wbImageView)
    AppCompatImageView wbImageView;

    private long exitTimeLong;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_login;
    }

    @Override
    protected void initView() {
        exitTimeLong = 0L;
        setToolbar(mainToolbar, "登录");
        StatusBarUtils.setStatusBarMode(this, true, R.color.whiteSub);
    }

    @Override
    protected void initListener() {
        registerTextView.setOnClickListener(view -> BaseApplication.getInstance().start(getActivity(), RegisterActivity.class));

        findPassTextView.setOnClickListener(view -> BaseApplication.getInstance().start(getActivity(), FindPassActivity.class));

        loginTextView.setOnClickListener(view -> login());

        qqImageView.setOnClickListener(view -> {

        });

        wxImageView.setOnClickListener(view -> {

        });

        wbImageView.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), LoginWebActivity.class);
            intent.putExtra(BaseConstant.DATA_URL, BaseConstant.URL_LOGIN_WB);
            BaseApplication.getInstance().start(getActivity(), intent, BaseConstant.CODE_LOGIN);
        });
    }

    @Override
    protected void initData() {

    }

//    @Override
//    public void onReturn() {
//
//        if (System.currentTimeMillis() - exitTimeLong > BaseConstant.TIME_EXIT) {
//            BaseToast.getInstance().showReturnOneMoreTime();
//            exitTimeLong = System.currentTimeMillis();
//        } else {
//            super.onReturn();
//        }
//    }

    @Override
    public void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (res == RESULT_OK) {
            if (req == BaseConstant.CODE_LOGIN) {
                BaseToast.getInstance().show("登录成功！");
                String key = data.getStringExtra(BaseConstant.DATA_KEY);
                //MemberHttpClient.get().updateKey(key);
                BaseShared.getInstance().putString(BaseConstant.SHARED_KEY, key);
                BaseApplication.getInstance().start(getActivity(), MainActivity.class);
                BaseApplication.getInstance().finish(getActivity());
            }
        }
    }


    private void login() {

//        BaseApplication.get().hideKeyboard(getActivity());
//
//        String username = Objects.requireNonNull(usernameEditText.getText()).toString();
//        String password = Objects.requireNonNull(passwordEditText.getText()).toString();
//
//        if (TextUtils.isEmpty(username) || TextUtil.isEmail(password)) {
//            BaseToast.get().show("请输入所有的信息！");
//            return;
//        }
//
//        loginTextView.setEnabled(false);
//        loginTextView.setText("登录中...");
//
//        LoginModel.get().index(username, password, new BaseHttpListener() {
//            @Override
//            public void onSuccess(BaseBean baseBean) {
//                loginTextView.setEnabled(true);
//                loginTextView.setText("登 录");
//                BaseToast.get().show("登录成功！");
//                MemberHttpClient.get().updateKey(JsonUtil.getDatasString(baseBean.getDatas(), "key"));
//                BaseShared.get().putString(BaseConstant.SHARED_KEY, JsonUtil.getDatasString(baseBean.getDatas(), "key"));
//                BaseApplication.get().start(getActivity(), MainActivity.class);
//                BaseApplication.get().finish(getActivity());
//            }
//
//            @Override
//            public void onFailure(String reason) {
//                loginTextView.setEnabled(true);
//                loginTextView.setText("登 录");
//                BaseToast.get().show(reason);
//            }
//        });

    }
}