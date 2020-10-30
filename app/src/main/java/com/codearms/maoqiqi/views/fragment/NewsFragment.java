package com.codearms.maoqiqi.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.activity.SearchViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * NewsPage
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/21 21:03
 */
public class NewsFragment extends Fragment {

    private String[] titles = {"推荐", "热点", "社会", "娱乐", "情感", "故事", "小说", "星座", "科技", "财经", "体育", "军事", "教育", "历史", "搞笑", "奇闻", "游戏", "时尚", "养生", "美食", "旅行"};

    private View rootView;
    private TabLayout tabLayout;
    private ImageView ivNewsCategory;
    private ViewPager viewPager;

    private SectionsPagerAdapter adapter;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_news, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        tabLayout = rootView.findViewById(R.id.tab_layout);
        ivNewsCategory = rootView.findViewById(R.id.iv_news_category);
        viewPager = rootView.findViewById(R.id.view_pager);

        adapter = new SectionsPagerAdapter(getChildFragmentManager());
        for (String title : titles) {
            adapter.addFragment(title, getFragment(title));
        }

        // 设置ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setCurrentItem(0);

        tabLayout.setupWithViewPager(viewPager);
    }

    private Fragment getFragment(String title) {
        return NewsListFragment.newInstance(title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_news, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                startActivity(new Intent(getActivity(), SearchViewActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private final class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<String> fragmentTitles;

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            fragmentTitles = new ArrayList<>();
        }

        void addFragment(String title, Fragment fragment) {
            fragmentTitles.add(title);
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
}
