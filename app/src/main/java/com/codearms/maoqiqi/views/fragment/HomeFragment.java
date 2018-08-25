package com.codearms.maoqiqi.views.fragment;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.bumptech.glide.Glide;
import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.bean.HomeListBean;
import com.codearms.maoqiqi.views.utils.AssetsUtils;
import com.codearms.maoqiqi.views.utils.UrlMatch;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
    private RecyclerView recyclerView;

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
        recyclerView = rootView.findViewById(R.id.recycler_view);

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

        String json = AssetsUtils.getJson(getActivity(), "gank.json");
        HomeListBean homeListBean = new Gson().fromJson(json, HomeListBean.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), homeListBean.getResultList()));
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

    private final class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewViewHolder> {

        private Context context;
        private List<HomeListBean.ItemBean> data;

        RecyclerViewAdapter(Context context, List<HomeListBean.ItemBean> data) {
            this.context = context;
            this.data = data;
        }

        @NonNull
        @Override
        public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_home_list, parent, false);
            return new RecyclerViewViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
            HomeListBean.ItemBean itemBean = data.get(position);
            if (itemBean.getType().equals("福利")) {
                holder.ivWelfare.setVisibility(View.VISIBLE);
                holder.llInfo.setVisibility(View.GONE);
                setImage(holder.ivWelfare, itemBean.getUrl());
            } else {
                holder.ivWelfare.setVisibility(View.GONE);
                holder.llInfo.setVisibility(View.VISIBLE);
                holder.tvDes.setText(itemBean.getDesc());
                if (itemBean.getImages() != null && itemBean.getImages().size() > 0
                        && !TextUtils.isEmpty(itemBean.getImages().get(0))) {
                    holder.ivImage.setVisibility(View.VISIBLE);
                    setImage(holder.ivImage, itemBean.getImages().get(0));
                } else {
                    holder.ivImage.setVisibility(View.GONE);
                }
            }

            setTag(holder.tvTag, itemBean.getType(), itemBean.getUrl());

            String name = itemBean.getWho() == null ? "佚名" : itemBean.getWho();
            holder.tvTime.setText(name + " " + getTranslateTime(itemBean.getPublishedAt()));

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }

        private void setImage(ImageView imageView, String imageUrl) {
            Glide.with(context).load(imageUrl).into(imageView);
        }

        private void setTag(TextView tvTag, String type, String url) {
            if (type.equals("福利")) {
                tvTag.setVisibility(View.GONE);
                return;
            }
            String prefix = type + " · ";

            String key = UrlMatch.processUrl(url);
            GradientDrawable drawable = (GradientDrawable) tvTag.getBackground();
            if (UrlMatch.URL_2_CONTENT.containsKey(key)) {
                drawable.setColor(UrlMatch.URL_2_COLOR.get(key));
                tvTag.setText(prefix + UrlMatch.URL_2_CONTENT.get(key));
            } else {
                if (type.equals("休息视频")) {
                    drawable.setColor(UrlMatch.VIDEO_COLOR);
                    tvTag.setText(prefix + UrlMatch.VIDEO_CONTENT);
                } else {
                    // gitHub 的要特殊处理
                    if (url.contains(UrlMatch.GITHUB_PREFIX)) {
                        drawable.setColor(UrlMatch.GITHUB_COLOR);
                        tvTag.setText(prefix + UrlMatch.GITHUB_CONTENT);
                    } else {
                        drawable.setColor(UrlMatch.OTHER_BLOG_COLOR);
                        tvTag.setText(prefix + UrlMatch.OTHER_BLOG_CONTENT);
                    }
                }
            }
            tvTag.setVisibility(View.VISIBLE);
        }

        /**
         * 如果在1分钟之内发布的显示"刚刚" 如果在1个小时之内发布的显示"XX分钟之前" 如果在1天之内发布的显示"XX小时之前"
         * 如果在今年的1天之外的只显示“月-日”,例如“05-03” 如果不是今年的显示“年-月-日”,例如“2014-03-11”
         */
        private String getTranslateTime(String time) {
            long timeMilliseconds = 0;
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            // 在主页面中设置当天时间
            Date nowTime = new Date();
            String currDate = sdf1.format(nowTime);
            long currentMilliseconds = nowTime.getTime();// 当前日期的毫秒值
            Date date = null;
            try {
                // 将给定的字符串中的日期提取出来
                date = sdf1.parse(time);
            } catch (Exception e) {
                e.printStackTrace();
                return time;
            }
            if (date != null) {
                timeMilliseconds = date.getTime();
            }

            long timeDifferent = currentMilliseconds - timeMilliseconds;


            if (timeDifferent < 60000) {// 一分钟之内
                return "刚刚";
            }
            if (timeDifferent < 3600000) {// 一小时之内
                long longMinute = timeDifferent / 60000;
                int minute = (int) (longMinute % 100);
                return minute + "分钟之前";
            }
            long l = 24 * 60 * 60 * 1000; // 每天的毫秒数
            if (timeDifferent < l) {// 小于一天
                long longHour = timeDifferent / 3600000;
                int hour = (int) (longHour % 100);
                return hour + "小时之前";
            }
            if (timeDifferent >= l) {
                String currYear = currDate.substring(0, 4);
                String year = time.substring(0, 4);
                if (!year.equals(currYear)) {
                    return time.substring(0, 10);
                }
                return time.substring(5, 10);
            }
            return time;
        }
    }

    private final class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView ivWelfare;
        LinearLayout llInfo;
        TextView tvDes;
        ImageView ivImage;
        TextView tvTag;
        TextView tvTime;

        RecyclerViewViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            ivWelfare = itemView.findViewById(R.id.iv_welfare);
            llInfo = itemView.findViewById(R.id.ll_info);
            tvDes = itemView.findViewById(R.id.tv_des);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvTag = itemView.findViewById(R.id.tv_tag);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
