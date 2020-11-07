package org.cloud.panzer.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.AreaBean;
import org.cloud.panzer.bean.StoreBuyBean;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class StoreBuyListAdapter extends RecyclerView.Adapter<StoreBuyListAdapter.ViewHolder> {

    private final ArrayList<StoreBuyBean> arrayList;

    public StoreBuyListAdapter(ArrayList<StoreBuyBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_store_buy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final StoreBuyBean bean = this.arrayList.get(position);

        App.getInstance().setRecyclerView(App.getInstance(), holder.mainRecyclerView, new GoodsBuyListAdapter(bean.getGoodsList()));
        holder.storeNameTextView.setText(bean.getStoreName());
        holder.logisticsMoneyTextView.setText("运费：￥");
        holder.logisticsMoneyTextView.append(bean.getLogisticsMoney());
        holder.totalMoneyTextView.setText("￥");
        holder.totalMoneyTextView.append(bean.getTotalMoney());
        holder.voucherLineView.setVisibility(View.GONE);
        holder.voucherRelativeLayout.setVisibility(View.GONE);
        if (bean.getStoreVoucherInfo() != null) {
            holder.voucherLineView.setVisibility(View.VISIBLE);
            holder.voucherRelativeLayout.setVisibility(View.VISIBLE);
            holder.voucherMoneyTextView.setText("节省￥");
            holder.voucherMoneyTextView.append(bean.getStoreVoucherInfo().getVoucherPrice());
        }
        if (bean.getStoreMansongRuleList() == null) {
            holder.mansongLineView.setVisibility(View.GONE);
            holder.manSongLinearLayout.setVisibility(View.GONE);
        } else {
            holder.mansongLineView.setVisibility(View.VISIBLE);
            holder.manSongLinearLayout.setVisibility(View.VISIBLE);
            holder.manSongDescTextView.setText(bean.getStoreMansongRuleList().getDesc().getDesc());
            BaseImageLoader.getInstance().display(bean.getStoreMansongRuleList().getDesc().getUrl(), holder.manSongGoodsImageView);
        }
        holder.messageEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                arrayList.get(positionInt).setMessage(Objects.requireNonNull(holder.messageEditText.getText()).toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.logisticsMoneyTextView)
        AppCompatTextView logisticsMoneyTextView;
        @BindView(R.id.mainRecyclerView)
        RecyclerView mainRecyclerView;
        @BindView(R.id.manSongDescTextView)
        AppCompatTextView manSongDescTextView;
        @BindView(R.id.manSongGoodsImageView)
        AppCompatImageView manSongGoodsImageView;
        @BindView(R.id.manSongLinearLayout)
        LinearLayoutCompat manSongLinearLayout;
        @BindView(R.id.mansongLineView)
        View mansongLineView;
        @BindView(R.id.messageEditText)
        AppCompatEditText messageEditText;
        @BindView(R.id.storeNameTextView)
        AppCompatTextView storeNameTextView;
        @BindView(R.id.totalMoneyTextView)
        AppCompatTextView totalMoneyTextView;
        @BindView(R.id.voucherLineView)
        View voucherLineView;
        @BindView(R.id.voucherMoneyTextView)
        AppCompatTextView voucherMoneyTextView;
        @BindView(R.id.voucherRelativeLayout)
        RelativeLayout voucherRelativeLayout;

        private ViewHolder(View view) {
            super(view);
        }
    }
}