package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.R;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchKeyListAdapter extends RecyclerView.Adapter<SearchKeyListAdapter.ViewHolder> {

    private final ArrayList<String> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public SearchKeyListAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_search_key, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final int positionInt = position;
        final String str = this.arrayList.get(position);

        holder.nameTextView.setText(str);
        holder.mainLinearLayout.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, str);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position, String str);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.nameTextView)
        AppCompatTextView nameTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}
