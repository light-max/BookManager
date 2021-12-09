package com.lpf.book.ui.shelf.borrow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.lpf.book.data.result.BorrowDetails;

public class BorrowedBookAdapter extends SimpleSingleItemRecyclerAdapter<BorrowDetails> {
    @Override
    protected int getItemViewLayout() {
        return R.layout.item_borrowed_book;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, BorrowDetails data, int position) {
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl(data.getCover()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(holder.getImage(R.id.icon));
        holder.setText(R.id.name, data.getName())
                .setText(R.id.author, "作者：" + data.getAuthor())
                .setText(R.id.initiate, "申请时间：" + data.getInitiateString())
                .setText(R.id.finish, "受理时间：" + data.getFinishString())
                .setText(R.id.borrow_time, "借阅时间：" + data.getBorrowTime());
    }
}
