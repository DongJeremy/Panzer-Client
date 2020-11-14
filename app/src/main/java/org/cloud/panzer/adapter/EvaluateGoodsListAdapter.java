package org.cloud.panzer.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.core.utils.ImageUtils;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.EvaluateGoodsBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * FileName: EvaluateGoodsListAdapter
 * Author: Admin
 * Date: 2020/11/11 12:56
 * Description: EvaluateGoodsListAdapter
 */
public class EvaluateGoodsListAdapter extends RecyclerView.Adapter<EvaluateGoodsListAdapter.ViewHolder> {

    private final ArrayList<EvaluateGoodsBean> arrayList;
    public OnItemClickListener onItemClickListener = null;

    public EvaluateGoodsListAdapter(ArrayList<EvaluateGoodsBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_evaluate_goods, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final EvaluateGoodsBean evaluateGoodsBean = this.arrayList.get(position);
        final int positionInt = position;

        ImageUtils.getInstance().display(evaluateGoodsBean.getMemberAvatar(), holder.mainImageView);
        holder.nameTextView.setText(evaluateGoodsBean.getGevalFrommembername());
        holder.timeTextView.setText(evaluateGoodsBean.getGevalAddtimeDate());
        holder.scoreRatingBar.setRating(Float.parseFloat(evaluateGoodsBean.getGevalScores()));
        holder.contentTextView.setText(evaluateGoodsBean.getGevalContent());
        if (evaluateGoodsBean.getGevalImage240().size() == 0) {
            holder.imageLinearLayout.setVisibility(View.GONE);
        } else {
            holder.imageLinearLayout.setVisibility(View.VISIBLE);
            holder.evaImageView = new AppCompatImageView[5];
            holder.evaImageView[0] = holder.oneImageView;
            holder.evaImageView[1] = holder.twoImageView;
            holder.evaImageView[2] = holder.thrImageView;
            holder.evaImageView[3] = holder.fouImageView;
            holder.evaImageView[4] = holder.fivImageView;
            for (AppCompatImageView visibility : holder.evaImageView) {
                visibility.setVisibility(View.GONE);
            }
            for (int i = 0; i < evaluateGoodsBean.getGevalImage240().size(); i++) {
                if (i < 5) {
                    holder.evaImageView[i].setVisibility(View.VISIBLE);
                    ImageUtils.getInstance().display(evaluateGoodsBean.getGevalImage240().get(i), holder.evaImageView[i]);
                }
            }
        }
        if (TextUtils.isEmpty(evaluateGoodsBean.getGevalExplain())) {
            holder.explainTextView.setVisibility(View.GONE);
        } else {
            holder.explainTextView.setVisibility(View.VISIBLE);
            holder.explainTextView.setText("掌柜回复：");
            holder.explainTextView.append(evaluateGoodsBean.getGevalExplain());
        }
        holder.appendTimeTextView.setVisibility(View.GONE);
        holder.appendTextView.setVisibility(View.GONE);
        holder.appendLinearLayout.setVisibility(View.GONE);
        holder.appendExplainTextView.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(evaluateGoodsBean.getGevalContentAgain())) {
            holder.appendTimeTextView.setVisibility(View.VISIBLE);
            holder.appendTextView.setVisibility(View.VISIBLE);
            holder.appendTimeTextView.setText(evaluateGoodsBean.getGevalAddtimeAgainDate());
            holder.appendTimeTextView.append(" 追加评价");
            holder.appendTextView.setText(evaluateGoodsBean.getGevalContentAgain());
            if (evaluateGoodsBean.getGevalImageAgain240().size() != 0) {
                holder.appendLinearLayout.setVisibility(View.VISIBLE);
                holder.appendImageView = new AppCompatImageView[5];
                holder.appendImageView[0] = holder.appendOneImageView;
                holder.appendImageView[1] = holder.appendTwoImageView;
                holder.appendImageView[2] = holder.appendThrImageView;
                holder.appendImageView[3] = holder.appendFouImageView;
                holder.appendImageView[4] = holder.appendFivImageView;
                for (AppCompatImageView visibility2 : holder.appendImageView) {
                    visibility2.setVisibility(View.GONE);
                }
                for (int i = 0; i < evaluateGoodsBean.getGevalImageAgain240().size(); i++) {
                    if (i < 5) {
                        holder.appendImageView[i].setVisibility(View.VISIBLE);
                        ImageUtils.getInstance().display(evaluateGoodsBean.getGevalImageAgain240().get(i), holder.appendImageView[i]);
                    }
                }
            }
            if (!TextUtils.isEmpty(evaluateGoodsBean.getGevalExplainAgain())) {
                holder.appendExplainTextView.setVisibility(View.VISIBLE);
                holder.appendExplainTextView.setText("掌柜回复：");
                holder.appendExplainTextView.append(evaluateGoodsBean.getGevalExplainAgain());
            }
        }
        holder.mainLinearLayout.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, evaluateGoodsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position, EvaluateGoodsBean evaluateGoodsBean);
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.appendExplainTextView)
        AppCompatTextView appendExplainTextView;
        @BindView(R.id.appendFivImageView)
        AppCompatImageView appendFivImageView;
        @BindView(R.id.appendFouImageView)
        AppCompatImageView appendFouImageView;
        @BindView(R.id.appendLinearLayout)
        LinearLayoutCompat appendLinearLayout;
        @BindView(R.id.appendOneImageView)
        AppCompatImageView appendOneImageView;
        @BindView(R.id.appendTextView)
        AppCompatTextView appendTextView;
        @BindView(R.id.appendThrImageView)
        AppCompatImageView appendThrImageView;
        @BindView(R.id.appendTimeTextView)
        AppCompatTextView appendTimeTextView;
        @BindView(R.id.appendTwoImageView)
        AppCompatImageView appendTwoImageView;
        @BindView(R.id.contentTextView)
        AppCompatTextView contentTextView;
        @BindView(R.id.explainTextView)
        AppCompatTextView explainTextView;
        @BindView(R.id.fivImageView)
        AppCompatImageView fivImageView;
        @BindView(R.id.fouImageView)
        AppCompatImageView fouImageView;
        @BindView(R.id.imageLinearLayout)
        LinearLayoutCompat imageLinearLayout;
        @BindView(R.id.mainImageView)
        AppCompatImageView mainImageView;
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.nameTextView)
        AppCompatTextView nameTextView;
        @BindView(R.id.oneImageView)
        AppCompatImageView oneImageView;
        @BindView(R.id.scoreRatingBar)
        AppCompatRatingBar scoreRatingBar;
        @BindView(R.id.thrImageView)
        AppCompatImageView thrImageView;
        @BindView(R.id.timeTextView)
        AppCompatTextView timeTextView;
        @BindView(R.id.twoImageView)
        AppCompatImageView twoImageView;

        public AppCompatImageView[] appendImageView;
        public AppCompatImageView[] evaImageView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}
