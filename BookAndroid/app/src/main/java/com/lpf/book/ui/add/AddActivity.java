package com.lpf.book.ui.add;

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
import com.lpf.book.ui.add.book.BookFragment;
import com.lpf.book.ui.add.book.PagerSelectData;
import com.lpf.book.ui.add.novel.NovelFragment;
import com.lpf.book.ui.add.recom.RecomFragment;

public class AddActivity extends NoMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        click(R.id.back, this::finish);
        String[] titles = {
                "热门推荐",
                "馆藏图书",
                "电子资源",
        };
        TabLayout tab = get(R.id.tab);
        ViewPager pager = get(R.id.pager);
        Fragment[] fragments = {
                new RecomFragment(),
                new BookFragment(),
                new NovelFragment(),
        };
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                TextView textView = get(R.id.title);
                textView.setText(titles[position]);
            }
        });
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), 0) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tab.setupWithViewPager(pager);
        PagerSelectData.getInstance().observe(this, integer -> {
            pager.setCurrentItem(integer);
        });
    }
}
