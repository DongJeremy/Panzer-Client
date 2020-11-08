package org.cloud.panzer.ui.order;

import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseViewPagerAdapter;
import org.cloud.core.widget.PullRefreshView;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.OrderListAdapter;
import org.cloud.panzer.bean.OrderBean;
import org.cloud.panzer.mvp.contract.OrderContract;
import org.cloud.panzer.mvp.presenter.OrderPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class OrderActivity extends BaseMvpActivity<OrderPresenter> implements OrderContract.View {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.searchEditText)
    AppCompatEditText searchEditText;
    @BindView(R.id.toolbarImageView)
    AppCompatImageView toolbarImageView;
    @BindView(R.id.mainTabLayout)
    TabLayout mainTabLayout;
    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;

    private OrderListAdapter[] mainAdapter;
    private PullRefreshView[] mainPullRefreshView;
    private ArrayList<OrderBean>[] mainArrayList;

    private int[] pageInt;
    private String keyword;
    private int positionInt;
    private boolean refreshBoolean;
    private String[] stateTypeString;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_order;
    }

    @Override
    protected void initView() {
        positionInt = getIntent().getIntExtra(BaseConstant.DATA_POSITION, 0);

        setToolbar(mainToolbar);
        toolbarImageView.setImageResource(R.drawable.ic_action_search);

        stateTypeString = new String[5];
        stateTypeString[0] = "";
        stateTypeString[1] = "state_new";
        stateTypeString[2] = "state_send";
        stateTypeString[3] = "state_notakes";
        stateTypeString[4] = "state_noeval";

        List<String> titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("待付款");
        titleList.add("待收货");
        titleList.add("待自提");
        titleList.add("待评价");

        List<View> viewList = new ArrayList<>();
        viewList.add(getLayoutInflater().inflate(R.layout.include_order_recycler_view, null));
        viewList.add(getLayoutInflater().inflate(R.layout.include_order_recycler_view, null));
        viewList.add(getLayoutInflater().inflate(R.layout.include_order_recycler_view, null));
        viewList.add(getLayoutInflater().inflate(R.layout.include_order_recycler_view, null));
        viewList.add(getLayoutInflater().inflate(R.layout.include_order_recycler_view, null));

        keyword = "";
        refreshBoolean = false;
        pageInt = new int[viewList.size()];
        //noinspection unchecked
        mainArrayList = new ArrayList[viewList.size()];
        mainAdapter = new OrderListAdapter[viewList.size()];
        mainPullRefreshView = new PullRefreshView[viewList.size()];

        for (int i = 0; i < viewList.size(); i++) {
            pageInt[i] = 1;
            mainArrayList[i] = new ArrayList<>();
            mainAdapter[i] = new OrderListAdapter(mainArrayList[i]);
            mainPullRefreshView[i] = viewList.get(i).findViewById(R.id.mainPullRefreshView);
            mainTabLayout.addTab(mainTabLayout.newTab().setText(titleList.get(i)));
            mainPullRefreshView[i].getRecyclerView().setAdapter(mainAdapter[i]);
        }

        App.getInstance().setTabLayout(mainTabLayout, new BaseViewPagerAdapter(viewList, titleList), mainViewPager);
        mainTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mainViewPager.setCurrentItem(positionInt);
    }

    @Override
    protected void initListener() {
        toolbarImageView.setOnClickListener(view -> {
            App.getInstance().hideKeyboard(getActivity());
            keyword = Objects.requireNonNull(searchEditText.getText()).toString();
            pageInt[positionInt] = 1;
            getOrder();
        });

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                positionInt = position;
                if (mainArrayList[positionInt].size() == 0) {
                    getOrder();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (PullRefreshView pullRefreshView : mainPullRefreshView) {
            pullRefreshView.setOnRefreshListener(new PullRefreshView.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    pageInt[positionInt] = 1;
                    getOrder();
                }

                @Override
                public void onLoadMore() {
                    getOrder();
                }
            });
        }

        for (OrderListAdapter orderListAdapter : mainAdapter) {
            orderListAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
                @Override
                public void onPay(int position, OrderBean orderBean) {
                    App.getInstance().startOrderPay(getActivity(), orderBean.getPaySn());
                    refreshBoolean = true;
                }

                @Override
                public void onClick(int position, OrderBean orderBean) {

                }

                @Override
                public void onItemClick(int position, int itemPosition, OrderBean.OrderListBean orderListBean) {
                    orderDetailed(orderListBean.getOrderId());
                }

                @Override
                public void onItemGoodsClick(int position, int itemPosition, OrderBean.OrderListBean orderListBean) {
                    orderDetailed(orderListBean.getOrderId());
                }

                @Override
                public void onOption(int position, int itemPosition, OrderBean.OrderListBean orderListBean) {
                    switch (orderListBean.getOrderState()) {
                        case "0":
                            //订单详细
                            orderDetailed(orderListBean.getOrderId());
                            break;
                        case "10":
                            //订单详细
                            orderDetailed(orderListBean.getOrderId());
                            break;
                        case "20":
                            if (orderListBean.getLockState().equals("0")) {
                                //订单详细
                                orderDetailed(orderListBean.getOrderId());
                            }
                        case "30":
                            if (orderListBean.getLockState().equals("0")) {
                                //查看物流
                                orderLogistics(orderListBean.getShippingCode());
                            } else {
                                //查看物流
                                orderLogistics(orderListBean.getShippingCode());
                            }
                            break;
                        case "40":
                            if (orderListBean.getEvaluationState().equals("1")) {
                                if (orderListBean.getEvaluationAgainState().equals("1")) {
                                    //删除订单
                                    orderDelete(orderListBean.getOrderId());
                                } else {
                                    //删除订单
                                    orderDelete(orderListBean.getOrderId());
                                }
                            } else {
                                //订单详细
                                orderDetailed(orderListBean.getOrderId());
                            }
                            break;
                    }
                }

                @Override
                public void onOpera(int position, int itemPosition, OrderBean.OrderListBean orderListBean) {
                    switch (orderListBean.getOrderState()) {
                        case "0":
                            //删除订单
                            orderDelete(orderListBean.getOrderId());
                            break;
                        case "10":
                            //取消订单
                            orderCancel(orderListBean.getOrderId());
                            break;
                        case "20":
                            if (orderListBean.getLockState().equals("0")) {
                                //申请退款
                                orderRefund(orderListBean.getOrderId());
                            } else {
                                //订单详细
                                orderDetailed(orderListBean.getOrderId());
                            }
                            break;
                        case "30":
                            if (orderListBean.getLockState().equals("0")) {
                                //确认收货
                                orderReceive(orderListBean.getOrderId());
                            } else {
                                //订单详细
                                orderDetailed(orderListBean.getOrderId());
                            }
                            break;
                        case "40":
                            if (orderListBean.getEvaluationState().equals("1")) {
                                if (orderListBean.getEvaluationAgainState().equals("1")) {
                                    //订单详细
                                    orderDetailed(orderListBean.getOrderId());
                                } else {
                                    //追加评价
                                    orderEvaluateAgain(orderListBean.getOrderId());
                                }
                            } else {
                                //订单评价
                                orderEvaluate(orderListBean.getOrderId());
                            }
                            break;
                    }
                }
            });
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (refreshBoolean) {
            refreshBoolean = false;
            pageInt[positionInt] = 1;
            getOrder();
        }
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    public void showAreaList(String areaData, String type) {

    }

    //自定义方法

    private void getOrder() {

        mainPullRefreshView[positionInt].setLoading();

        MemberOrderModel.get().orderList(stateTypeString[positionInt], keyword, pageInt[positionInt] + "", new BaseHttpListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                if (pageInt[positionInt] == 1) {
                    mainArrayList[positionInt].clear();
                }
                if (pageInt[positionInt] <= baseBean.getPageTotal()) {
                    String data = JsonUtil.getDatasString(baseBean.getDatas(), "order_group_list");
                    mainArrayList[positionInt].addAll(Objects.requireNonNull(JsonUtil.json2ArrayList(data, OrderBean.class)));
                    pageInt[positionInt]++;
                }
                mainPullRefreshView[positionInt].setComplete();
            }

            @Override
            public void onFailure(String reason) {
                mainPullRefreshView[positionInt].setFailure();
            }
        });

    }

    private void orderDelete(String orderId) {
        mPresenter.

        MemberOrderModel.get().orderDelete(orderId, new BaseHttpListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                pageInt[positionInt] = 1;
                getOrder();
            }

            @Override
            public void onFailure(String reason) {
                BaseToast.get().show(reason);
            }
        });

    }

    private void orderCancel(String orderId) {

        MemberOrderModel.get().orderCancel(orderId, new BaseHttpListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                pageInt[positionInt] = 1;
                getOrder();
            }

            @Override
            public void onFailure(String reason) {
                BaseToast.get().show(reason);
            }
        });

    }

    private void orderReceive(String orderId) {

        MemberOrderModel.get().orderReceive(orderId, new BaseHttpListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                pageInt[positionInt] = 1;
                getOrder();
            }

            @Override
            public void onFailure(String reason) {
                BaseToast.get().show(reason);
            }
        });

    }

    private void orderLogistics(String number) {

        Intent intent = new Intent(getActivity(), LogisticsActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, number);
        BaseApplication.get().start(getActivity(), intent);

    }

    private void orderRefund(String orderId) {

        Intent intent = new Intent(getActivity(), RefundApplyActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, orderId);
        intent.putExtra(BaseConstant.DATA_GOODSID, "");
        BaseApplication.get().start(getActivity(), intent);
        refreshBoolean = true;

    }

    private void orderDetailed(String orderId) {

        Intent intent = new Intent(getActivity(), DetailedActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, orderId);
        BaseApplication.get().start(getActivity(), intent);
        refreshBoolean = true;

    }

    private void orderEvaluate(String orderId) {

        Intent intent = new Intent(getActivity(), EvaluateActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, orderId);
        BaseApplication.get().start(getActivity(), intent);
        refreshBoolean = true;

    }

    private void orderEvaluateAgain(String orderId) {

        Intent intent = new Intent(getActivity(), EvaluateAgainActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, orderId);
        BaseApplication.get().start(getActivity(), intent);
        refreshBoolean = true;

    }
}