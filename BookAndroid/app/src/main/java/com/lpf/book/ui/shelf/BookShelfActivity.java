package com.lpf.book.ui.shelf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lpf.book.R;
import com.lpf.book.base.activity.NoMvpActivity;
import com.lpf.book.ui.add.AddActivity;
import com.lpf.book.ui.people.PeopleActivity;
import com.lpf.book.ui.shelf.borrow.BorrowShelfFragment;
import com.lpf.book.ui.shelf.novel.NovelShelfFragment;

public class BookShelfActivity extends NoMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_shelf;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        BorrowShelfFragment borrow = new BorrowShelfFragment();
        NovelShelfFragment ebook = new NovelShelfFragment();
        TabLayout tab = get(R.id.tab);
        ViewPager pager = get(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), 0) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return new Fragment[]{borrow, ebook}[position];
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return new String[]{"已借阅书籍", "已下载电子书"}[position];
            }
        });
        tab.setupWithViewPager(pager);
        getAdd().setOnClickListener(v -> {
            open(AddActivity.class);
        });
        getPeople().setOnClickListener(v -> {
            Intent intent = new Intent(this, PeopleActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    public TextView getAdd() {
        return get(R.id.add);
    }

    public TextView getPeople() {
        return get(R.id.people);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            finish();
        }
    }
}
