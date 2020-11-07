package org.cloud.panzer.ui.common;

import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseShared;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.core.utils.StringUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.LoginContract;
import org.cloud.panzer.mvp.presenter.LoginPresenter;

import java.util.Objects;

import butterknife.BindView;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {

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
        registerTextView.setOnClickListener(view -> App.getInstance().startRegister(getActivity()));

        findPassTextView.setOnClickListener(view -> App.getInstance().startFindPass(getActivity()));

        loginTextView.setOnClickListener(view -> login());

        qqImageView.setOnClickListener(view -> {

        });

        wxImageView.setOnClickListener(view -> {

        });

        wbImageView.setOnClickListener(view -> {
//            Intent intent = new Intent(getActivity(), LoginWebActivity.class);
//            intent.putExtra(BaseConstant.DATA_URL, BaseConstant.URL_LOGIN_WB);
//            App.getInstance().start(getActivity(), intent, BaseConstant.CODE_LOGIN);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showLoginSuccess(String loginData) {
        JsonObject rootJsonObject = new JsonParser().parse(loginData).getAsJsonObject();
        int code = rootJsonObject.get("code").getAsInt();
        if (code != 200) {
            loginTextView.setEnabled(true);
            loginTextView.setText("登 录");
            BaseToast.getInstance().show("用户名或密码错误");
            return;
        }
        JsonObject jsonObject = rootJsonObject.getAsJsonObject("datas");
        String key = jsonObject.get("key").getAsString();
        BaseShared.getInstance().putString(BaseConstant.SHARED_KEY, key);
        App.getInstance().startMain(getActivity());
        App.getInstance().finish(getActivity());
    }

    @Override
    public void showError(String reason) {
        loginTextView.setEnabled(true);
        loginTextView.setText("登 录");
        BaseToast.getInstance().show(reason);
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

//    @Override
//    public void onActivityResult(int req, int res, Intent data) {
//        super.onActivityResult(req, res, data);
//        if (res == RESULT_OK) {
//            if (req == BaseConstant.CODE_LOGIN) {
//                BaseToast.getInstance().show("登录成功！");
//                String key = data.getStringExtra(BaseConstant.DATA_KEY);
//                //MemberHttpClient.get().updateKey(key);
//                BaseShared.getInstance().putString(BaseConstant.SHARED_KEY, key);
//                App.getInstance().startMain(getActivity());
//                App.getInstance().finish(getActivity());
//            }
//        }
//    }

    private void login() {
        App.getInstance().hideKeyboard(getActivity());

        String username = Objects.requireNonNull(usernameEditText.getText()).toString();
        String password = Objects.requireNonNull(passwordEditText.getText()).toString();

        if (TextUtils.isEmpty(username) || StringUtils.isEmail(password)) {
            BaseToast.getInstance().show("请输入所有的信息！");
            return;
        }

        loginTextView.setEnabled(false);
        loginTextView.setText("登录中...");
        mPresenter.requestLogin(username, password);
    }
}