package com.lpf.book.ui.add.book;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.lpf.book.data.result.Book;

public class BookListAdapter extends SimpleSingleItemRecyclerAdapter<Book> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_book;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, Book data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl("/cover/book/" + data.getId()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.icon));
        holder.setText(R.id.name, data.getName());
        holder.setText(R.id.author, "作者：" + data.getAuthor());
        holder.setText(R.id.des, data.getDes());
    }
}
