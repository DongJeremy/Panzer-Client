package org.cloud.panzer.ui.common;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseCountTime;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.SPUtils;
import org.cloud.core.utils.StringUtils;
import org.cloud.core.utils.Utils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.RegisterContract;
import org.cloud.panzer.mvp.presenter.RegisterPresenter;
import org.cloud.panzer.ui.main.MainActivity;
import org.cloud.panzer.view.CenterTextView;

import java.util.Objects;

import butterknife.BindView;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.captchaDelImageView)
    AppCompatImageView captchaDelImageView;
    @BindView(R.id.captchaEditText)
    AppCompatEditText captchaEditText;
    @BindView(R.id.captchaImageView)
    AppCompatImageView captchaImageView;
    @BindView(R.id.codeDelImageView)
    AppCompatImageView codeDelImageView;
    @BindView(R.id.codeEditText)
    AppCompatEditText codeEditText;
    @BindView(R.id.completeTextView)
    AppCompatTextView completeTextView;
    @BindView(R.id.confirmDelImageView)
    AppCompatImageView confirmDelImageView;
    @BindView(R.id.confirmEditText)
    AppCompatEditText confirmEditText;
    @BindView(R.id.emailDelImageView)
    AppCompatImageView emailDelImageView;
    @BindView(R.id.emailEditText)
    AppCompatEditText emailEditText;
    @BindView(R.id.getTextView)
    AppCompatTextView getTextView;
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.mobileCheckBox)
    AppCompatCheckBox mobileCheckBox;
    @BindView(R.id.mobileDelImageView)
    AppCompatImageView mobileDelImageView;
    @BindView(R.id.mobileEditText)
    AppCompatEditText mobileEditText;
    @BindView(R.id.mobileLinearLayout)
    LinearLayoutCompat mobileLinearLayout;
    @BindView(R.id.mobileProtocolTextView)
    AppCompatTextView mobileProtocolTextView;
    @BindView(R.id.mobileTextView)
    CenterTextView mobileTextView;
    @BindView(R.id.normalCheckBox)
    AppCompatCheckBox normalCheckBox;
    @BindView(R.id.normalLinearLayout)
    LinearLayoutCompat normalLinearLayout;
    @BindView(R.id.normalProtocolTextView)
    AppCompatTextView normalProtocolTextView;
    @BindView(R.id.normalTextView)
    CenterTextView normalTextView;
    @BindView(R.id.passwordDelImageView)
    AppCompatImageView passwordDelImageView;
    @BindView(R.id.passwordEditText)
    AppCompatEditText passwordEditText;
    @BindView(R.id.passwordSmsDelImageView)
    AppCompatImageView passwordSmsDelImageView;
    @BindView(R.id.passwordSmsEditText)
    AppCompatEditText passwordSmsEditText;
    @BindView(R.id.registerTextView)
    AppCompatTextView registerTextView;
    @BindView(R.id.typeLinearLayout)
    LinearLayoutCompat typeLinearLayout;
    @BindView(R.id.usernameDelImageView)
    AppCompatImageView usernameDelImageView;
    @BindView(R.id.usernameEditText)
    AppCompatEditText usernameEditText;

    public String codeKeyString;
    private Drawable mobileDrawable;
    private Drawable mobilePressDrawable;
    private Drawable normalDrawable;
    private Drawable normalPressDrawable;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_register;
    }

    @Override
    protected void initView() {
        this.normalDrawable = Utils.getMipmap(R.mipmap.ic_register_normal, R.color.greyAdd);
        this.mobileDrawable = Utils.getMipmap(R.mipmap.ic_register_mobile, R.color.greyAdd);
        this.normalPressDrawable = Utils.getMipmap(R.mipmap.ic_register_normal_press);
        this.mobilePressDrawable = Utils.getMipmap(R.mipmap.ic_register_mobile_press);
        setToolbar(this.mainToolbar, "会员注册");
        this.codeKeyString = "";
        makeCodeKey();
    }

    @Override
    protected void initListener() {
        this.normalTextView.setOnClickListener(view -> {
            this.normalTextView.setTextColor(Utils.getColors(R.color.primary));
            this.normalTextView.setCompoundDrawablesWithIntrinsicBounds(this.normalPressDrawable, null, null, null);
            this.mobileTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            this.mobileTextView.setCompoundDrawablesWithIntrinsicBounds(this.mobileDrawable, null, null, null);
            this.normalLinearLayout.setVisibility(View.VISIBLE);
            this.mobileLinearLayout.setVisibility(View.GONE);
        });
        this.mobileTextView.setOnClickListener(view -> {
            this.normalTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            this.normalTextView.setCompoundDrawablesWithIntrinsicBounds(this.normalDrawable, null, null, null);
            this.mobileTextView.setTextColor(Utils.getColors(R.color.primary));
            this.mobileTextView.setCompoundDrawablesWithIntrinsicBounds(this.mobilePressDrawable, null, null, null);
            this.normalLinearLayout.setVisibility(View.GONE);
            this.mobileLinearLayout.setVisibility(View.VISIBLE);

        });
        this.normalProtocolTextView.setOnClickListener(v -> App.getInstance().startUrl(getActivity(), BaseConstant.DOCUMENT));
        this.registerTextView.setOnClickListener(view -> register());
        this.getTextView.setOnClickListener(view -> getCode());
        this.mobileProtocolTextView.setOnClickListener(view -> App.getInstance().startUrl(getActivity(), BaseConstant.DOCUMENT));
        this.completeTextView.setOnClickListener(view -> checkCode());
        this.passwordDelImageView.setOnClickListener(view -> this.passwordEditText.setText(""));
        this.confirmDelImageView.setOnClickListener(view -> this.confirmEditText.setText(""));
        this.emailDelImageView.setOnClickListener(view -> this.emailEditText.setText(""));
        this.mobileDelImageView.setOnClickListener(view -> this.mobileEditText.setText(""));
        this.captchaDelImageView.setOnClickListener(view -> this.captchaEditText.setText(""));
        this.codeDelImageView.setOnClickListener(view -> this.codeEditText.setText(""));
        this.passwordSmsDelImageView.setOnClickListener(view -> this.passwordSmsEditText.setText(""));
        this.usernameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.usernameEditText.getText()).toString())) {
                    RegisterActivity.this.usernameDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.usernameDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.confirmEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.confirmEditText.getText()).toString())) {
                    RegisterActivity.this.confirmDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.confirmDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.emailEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.emailEditText.getText()).toString())) {
                    RegisterActivity.this.emailDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.emailDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.mobileEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.mobileEditText.getText()).toString())) {
                    RegisterActivity.this.mobileDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.mobileDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.captchaEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.captchaEditText.getText()).toString())) {
                    RegisterActivity.this.captchaDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.captchaDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.codeEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.codeEditText.getText()).toString())) {
                    RegisterActivity.this.codeDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.codeDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
        this.passwordSmsEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(Objects.requireNonNull(RegisterActivity.this.passwordSmsEditText.getText()).toString())) {
                    RegisterActivity.this.passwordSmsDelImageView.setVisibility(View.GONE);
                } else {
                    RegisterActivity.this.passwordSmsDelImageView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getState();
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void showStateSuccess(BaseBean baseBean) {
        if (baseBean.getDatas().equals("1")) {
            RegisterActivity.this.normalTextView.setTextColor(Utils.getColors(R.color.primary));
            RegisterActivity.this.normalTextView.setCompoundDrawablesWithIntrinsicBounds(RegisterActivity.this.normalPressDrawable,
                    null, null, null);
            RegisterActivity.this.mobileTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            RegisterActivity.this.mobileTextView.setCompoundDrawablesWithIntrinsicBounds(RegisterActivity.this.mobileDrawable,
                    null, null, null);
            RegisterActivity.this.typeLinearLayout.setVisibility(View.VISIBLE);
            RegisterActivity.this.normalLinearLayout.setVisibility(View.VISIBLE);
            RegisterActivity.this.mobileLinearLayout.setVisibility(View.GONE);
            return;
        }
        RegisterActivity.this.typeLinearLayout.setVisibility(View.GONE);
        RegisterActivity.this.normalLinearLayout.setVisibility(View.VISIBLE);
        RegisterActivity.this.mobileLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void showStateFail(String reason) {
        BaseToast.getInstance().show(reason);
    }

    private void getState() {
        mPresenter.requestGetState("connect_sms_reg");
    }

    @Override
    public void showRegisterSuccess(BaseBean baseBean) {
        RegisterActivity.this.registerTextView.setEnabled(true);
        RegisterActivity.this.registerTextView.setText("注册账号");
        BaseToast.getInstance().show("注册成功！");
        String key = JsonUtils.parseJsonToJsonObject(baseBean.getDatas()).get(BaseConstant.DATA_KEY).getAsString();
        SPUtils.getInstance().putString(BaseConstant.SHARED_KEY, key);
        App.getInstance().start(RegisterActivity.this.getActivity(), MainActivity.class);
        App.getInstance().finish(RegisterActivity.this.getActivity());
    }

    @Override
    public void showRegisterFail(String reason) {
        BaseToast.getInstance().show(reason);
        RegisterActivity.this.registerTextView.setText("注册账号");
        RegisterActivity.this.registerTextView.setEnabled(true);
    }

    private void register() {
        App.getInstance().hideKeyboard(getActivity());
        String username = Objects.requireNonNull(this.usernameEditText.getText()).toString();
        String password = Objects.requireNonNull(this.passwordEditText.getText()).toString();
        String confirm = Objects.requireNonNull(this.confirmEditText.getText()).toString();
        String email = Objects.requireNonNull(this.emailEditText.getText()).toString();
        if (!this.normalCheckBox.isChecked()) {
            BaseToast.getInstance().show("请同意用户注册协议...");
        } else if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm) || TextUtils.isEmpty(email)) {
            BaseToast.getInstance().show("请填写完所有的信息...");
        } else if (!password.equals(confirm)) {
            BaseToast.getInstance().show("两次输入的密码不一致...");
        } else if (!StringUtils.isEmail(email)) {
            BaseToast.getInstance().show("邮箱地址格式不正确...");
        } else {
            this.registerTextView.setEnabled(false);
            this.registerTextView.setText("注册中...");
            Log.e("TAG", Thread.currentThread().getStackTrace()[2].getMethodName());
            mPresenter.requestRegister(username, password, email);
        }
    }

    @Override
    public void showSmsCaptchaSuccess(BaseBean baseBean) {
        final int parseInt = JsonUtils.parseJsonToJsonObject(baseBean.getDatas()).get("sms_time").getAsInt();
        new BaseCountTime(parseInt * 1000, 1000) {
            int totalTime = parseInt;

            public void onTick(long j) {
                super.onTick(j);
                StringBuilder sb = new StringBuilder();
                sb.append("再次获取（");
                int i = this.totalTime;
                this.totalTime = i - 1;
                sb.append(i);
                sb.append(" S ）");
                RegisterActivity.this.getTextView.setText(sb.toString());
            }

            public void onFinish() {
                super.onFinish();
                RegisterActivity.this.getTextView.setEnabled(true);
                RegisterActivity.this.getTextView.setText("获取验证码");
                RegisterActivity.this.makeCodeKey();
            }
        }.start();
    }

    @Override
    public void showSmsCaptchaFail(String reason) {
        RegisterActivity.this.getTextView.setEnabled(true);
        RegisterActivity.this.getTextView.setText("获取验证码");
        BaseToast.getInstance().show(reason);
        RegisterActivity.this.makeCodeKey();
    }

    private void getCode() {
        App.getInstance().hideKeyboard(getActivity());
        String mobile = Objects.requireNonNull(this.mobileEditText.getText()).toString();
        String captcha = Objects.requireNonNull(this.captchaEditText.getText()).toString();
        if (!this.mobileCheckBox.isChecked()) {
            BaseToast.getInstance().show("请同意用户注册协议...");
        } else if (!StringUtils.isMobile(mobile)) {
            BaseToast.getInstance().show("手机号码格式不正确！");
        } else if (TextUtils.isEmpty(captcha)) {
            BaseToast.getInstance().show("请输入验证码");
        } else {
            this.getTextView.setEnabled(false);
            this.getTextView.setText("获取中...");
            mPresenter.requestGetSmsCaptcha("1", mobile, captcha, this.codeKeyString);
        }
    }

    private void checkCode() {
//        App.getInstance().hideKeyboard(getActivity());
//        String obj = Objects.requireNonNull(this.codeEditText.getText()).toString();
//        String obj2 = Objects.requireNonNull(this.mobileEditText.getText()).toString();
//        if (TextUtils.isEmpty(obj)) {
//            BaseToast.getInstance().show("请输入验证码！");
//        } else if (!StringUtils.isMobile(obj2)) {
//            BaseToast.getInstance().show("手机号码格式不正确！");
//        } else {
//            this.completeTextView.setEnabled(false);
//            this.completeTextView.setText("处理中...");
//            ConnectModel.get().checkSmsCaptcha("1", obj2, obj, new BaseHttpListener() {
//                public void onSuccess(BaseBean baseBean) {
//                    if (JsonUtil.isSuccess(baseBean.getDatas())) {
//                        RegisterActivity.this.smsRegister();
//                        return;
//                    }
//                    RegisterActivity.this.completeTextView.setEnabled(true);
//                    RegisterActivity.this.completeTextView.setText("完成注册");
//                    BaseToast.getInstance().showFailure();
//                    RegisterActivity.this.makeCodeKey();
//                }
//
//                public void onFailure(String str) {
//                    RegisterActivity.this.completeTextView.setEnabled(true);
//                    RegisterActivity.this.completeTextView.setText("完成注册");
//                    BaseToast.getInstance().show(str);
//                    RegisterActivity.this.makeCodeKey();
//                }
//            });
//        }
    }


    public void makeCodeKey() {
//        SeccodeModel.get().makeCodeKey(new BaseHttpListener() {
//            public void onSuccess(BaseBean baseBean) {
//                String unused = RegisterActivity.this.codeKeyString = JsonUtil.getDatasString(baseBean.getDatas(), "codekey");
//                BaseImageLoader.getInstance().display(SeccodeModel.get().makeCode(RegisterActivity.this.codeKeyString),
//                        (ImageView) RegisterActivity.this.captchaImageView);
//            }
//
//            public void onFailure(String str) {
//                BaseDialog.getInstance().query(RegisterActivity.this.getActivity(), "获取数据失败", str,
//                        (dialogInterface, i) -> RegisterActivity.this.makeCodeKey(),
//                        (dialogInterface, i) -> App.getInstance().finish(RegisterActivity.this.getActivity()));
//            }
//        });
    }

}