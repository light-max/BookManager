package com.lpf.book.ui.add.recom.book;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.lpf.book.data.result.RecomBook;

public class RecomBookListAdapter extends SimpleSingleItemRecyclerAdapter<RecomBook> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_recom_book;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, RecomBook data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getCover()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.icon));
        holder.setText(R.id.name, data.getName());
        holder.setText(R.id.author, "作者：" + data.getAuthor());
        holder.setText(R.id.borrow, "总借阅次数：" + data.getBorrowNumber());
        holder.setText(R.id.count, "书本总数：" + data.getCount());
    }
}
