package com.lpf.book.ui.add.recom.novel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.lpf.book.data.result.RecomNovel;

public class RecomNovelListAdapter extends SimpleSingleItemRecyclerAdapter<RecomNovel> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_recom_novel;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, RecomNovel data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl("/cover/novel/" + data.getName()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.icon));
        holder.setText(R.id.name, data.getName());
        holder.setText(R.id.count, "字数：" + data.getCountString());
        holder.setText(R.id.read, "浏览量：" + data.getReadCount());
    }
}
