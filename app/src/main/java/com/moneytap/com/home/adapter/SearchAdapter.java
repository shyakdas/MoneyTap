package com.moneytap.com.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.moneytap.com.R;
import com.moneytap.com.model.SearchModel;
import com.moneytap.com.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context mContext;
    private ArrayList<SearchModel.Page> mArrayList;

    public SearchAdapter(Context mContext) {
        this.mContext = mContext;
        this.mArrayList = new ArrayList<>();
    }

    public void add(SearchModel.Page r) {
        mArrayList.add(r);
        notifyItemInserted(mArrayList.size() - 1);
    }

    public void addAll(List<SearchModel.Page> moveResults) {
        for (SearchModel.Page result : moveResults) {
            add(result);
        }
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchAdapter.SearchViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int position) {
        searchViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size() > 0 ? mArrayList.size() : 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mProfileImage;
        private TextView mTitle, mDescription;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mProfileImage = itemView.findViewById(R.id.circleImageView);
            mTitle = itemView.findViewById(R.id.item_name);
            mDescription = itemView.findViewById(R.id.item_description);
        }

        public void bind(int position) {
            TextDrawable drawable = TextDrawable.builder()
                    .beginConfig()
                    .width(80)
                    .height(80)
                    .endConfig()
                    .buildRect(String.valueOf(mArrayList.get(position).getTitle().charAt(0)),
                            AppUtils.getRandomColor());
            if (mArrayList.get(position).getThumbnail().getSource() != null) {
                Glide.with(mContext).load(mArrayList.get(position).getThumbnail().getSource())
                        .error(drawable).into(mProfileImage);
            }
            if (mArrayList.get(position).getTitle() != null) {
                mTitle.setText(mArrayList.get(position).getTitle());
            }
            if (mArrayList.get(position).getTerms().getDescription() != null) {
                mDescription.setText(mArrayList.get(position).getTerms().getDescription().get(position));
            }
        }
    }
}
