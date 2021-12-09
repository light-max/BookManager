package com.lpf.book.ui.add.novel;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lpf.book.R;
import com.lpf.book.api.ExRequestBuilder;
import com.lpf.book.base.recycler.SimpleSingleItemRecyclerAdapter;
import com.lpf.book.data.result.Novel;

public class NovelListAdapter extends SimpleSingleItemRecyclerAdapter<Novel> {
    private final int width;

    public NovelListAdapter(int width) {
        this.width = width;
    }

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_novel;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, Novel data, int position) {
        ImageView icon = holder.getImage(R.id.icon);
        TextView name = holder.getText(R.id.name);
        ViewGroup.LayoutParams params = icon.getLayoutParams();
        params.width = width;
        params.height = (int) (width * 1.5);
        icon.setLayoutParams(params);
        Glide.with(holder.itemView)
                .load(ExRequestBuilder.getUrl("/cover/novel/" + data.getName()))
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(icon);
        name.setText(data.getName());
    }
}
