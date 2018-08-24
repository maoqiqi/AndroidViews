package com.codearms.maoqiqi.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codearms.maoqiqi.views.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * HomePage
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/21 21:03
 */
public class HomeFragment extends Fragment {

    // Banner 图
    public static final Integer[] BANNER_RES_IDS = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4, R.drawable.banner_5};
    public static final int[] ICONS = {
            R.drawable.ic_home_food, R.drawable.ic_home_movie, R.drawable.ic_home_hotel, R.drawable.ic_home_entertainment,
            R.drawable.ic_home_take_out, R.drawable.ic_home_parent_child, R.drawable.ic_home_beauty, R.drawable.ic_home_spot,
            R.drawable.ic_home_shopping, R.drawable.ic_home_market, R.drawable.ic_home_park, R.drawable.ic_home_bar,
            R.drawable.ic_home_life, R.drawable.ic_home_haircut, R.drawable.ic_home_body_building, R.drawable.ic_home_pedicure,
            R.drawable.ic_home_clothes, R.drawable.ic_home_marry, R.drawable.ic_home_furniture, R.drawable.ic_home_all_categories
    };

    private View rootView;
    private Banner banner;
    private ViewPager viewPager;
    private LinearLayout container;

    private int pageSize = 10;
    private List<View> views;

    private int currentItem = 0;
    private int lastPosition = currentItem;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        banner = rootView.findViewById(R.id.banner);
        viewPager = rootView.findViewById(R.id.view_pager);
        container = rootView.findViewById(R.id.container);

        if (getActivity() == null) return;

        List<Integer> imageUrls = new ArrayList<>();
        Collections.addAll(imageUrls, BANNER_RES_IDS);
        banner.setImages(imageUrls).setImageLoader(new MyImageLoader()).start();

        List<String> data = Arrays.asList(getActivity().getResources().getStringArray(R.array.data));
        int size = data.size();
        views = new ArrayList<>();
        for (int i = 0; i < size; i += pageSize) {
            GridView gridView = (GridView) View.inflate(getActivity(), R.layout.layout_grid_view, null);
            int end = (i + pageSize) > size ? size : i + pageSize;
            List<String> list = new ArrayList<>(data.subList(i, end));
            gridView.setAdapter(new GridViewAdapter(getActivity(), list, i));
            views.add(gridView);
            container.addView(getImageView());
        }
        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 取消圆点选中
                container.getChildAt(lastPosition).setBackgroundResource(R.drawable.ic_unselected);
                // 圆点选中
                container.getChildAt(position).setBackgroundResource(R.drawable.ic_selected);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(currentItem);
        container.getChildAt(currentItem).setBackgroundResource(R.drawable.ic_selected);
    }

    private ImageView getImageView() {
        int size = getResources().getDimensionPixelSize(R.dimen.four);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.setMargins(size, size, size, size);
        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(params);
        imageView.setBackgroundResource(R.drawable.ic_unselected);
        return imageView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_classify:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource((Integer) path);
        }
    }

    private class GridViewAdapter extends BaseAdapter {

        private Context context;
        private List<String> data;
        private int index;

        GridViewAdapter(Context context, List<String> data, int index) {
            this.context = context;
            this.data = data;
            this.index = index;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_grid_view, null);
                holder.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(data.get(position));
            holder.tv.setCompoundDrawablesWithIntrinsicBounds(0, ICONS[index + position], 0, 0);
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tv;
    }

    private class ViewPagerAdapter extends PagerAdapter {

        private List<View> views;

        ViewPagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views == null ? 0 : views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
