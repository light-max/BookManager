package com.lpf.book.ui.add.recom;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.lpf.book.data.result.RandRecom;

public class RecomListAdapter extends SimpleSingleItemRecyclerAdapter<RandRecom> {
    private final int width;

    public RecomListAdapter(int width) {
        this.width = width;
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_rand_recom;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, RandRecom data, int position) {
        ImageView icon = holder.getImage(R.id.icon);
        TextView name = holder.getText(R.id.name);
        ViewGroup.LayoutParams params = icon.getLayoutParams();
        params.width = width;
        params.height = (int) (width * 1.5);
        icon.setLayoutParams(params);
        holder.setText(R.id.name, data.getName());
        if (data.isBook()) {
            holder.setText(R.id.type, "图书");
            Glide.with(holder.itemView)
                    .load(ExRequestBuilder.getUrl("/cover/book/" + data.getId()))
                    .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(icon);
        } else {
            holder.setText(R.id.type, "小说");
            Glide.with(holder.itemView)
                    .load(ExRequestBuilder.getUrl("/cover/novel/" + data.getName()))
                    .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(icon);
        }
    }
}
