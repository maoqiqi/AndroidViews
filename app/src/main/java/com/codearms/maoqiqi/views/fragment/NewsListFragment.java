package com.codearms.maoqiqi.views.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codearms.maoqiqi.views.LazyLoadFragment;
import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.bean.NewsListBean;
import com.codearms.maoqiqi.views.utils.AssetsUtils;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 新闻列表
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/23 21:08
 */
public class NewsListFragment extends LazyLoadFragment {

    private static final int WHAT = 0;
    private static final int DELAY_MILLIS = 3000;

    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private MyHandler handler = new MyHandler(this);

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewsListFragment.
     */
    public static NewsListFragment newInstance(String category) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_news_list, container, false);
        return rootView;
    }

    @Override
    public void loadData() {
        super.loadData();
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout);
        recyclerView = rootView.findViewById(R.id.recycler_view);

        if (getActivity() == null) return;
        String json = AssetsUtils.getJson(getActivity(), "news.json");
        NewsListBean newsListBean = new Gson().fromJson(json, NewsListBean.class);


        swipeRefreshLayout.setColorSchemeColors(Color.GRAY, Color.RED, Color.GREEN, Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(WHAT, DELAY_MILLIS);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), newsListBean.getNewsBeans()));
    }

    private static final class MyHandler extends Handler {

        private WeakReference<NewsListFragment> weakReference;

        MyHandler(NewsListFragment fragment) {
            this.weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT:
                    if (weakReference.get() != null) {
                        NewsListFragment fragment = weakReference.get();
                        fragment.swipeRefreshLayout.setRefreshing(false);
                    }
                    break;
            }
        }
    }

    private final class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

        private Context context;
        private List<NewsListBean.NewsBean> data;

        RecyclerViewAdapter(Context context, List<NewsListBean.NewsBean> data) {
            this.context = context;
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (position == 0) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.cardView.getLayoutParams();
                layoutParams.setMargins(layoutParams.leftMargin, context.getResources().getDimensionPixelSize(R.dimen.twelve), layoutParams.rightMargin, layoutParams.bottomMargin);
            }
            Glide.with(context).load(data.get(position).getImages().get(0)).into(holder.ivNews);
            holder.tvTitle.setText(data.get(position).getTitle());
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
    }

    private final class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView ivNews;
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            ivNews = itemView.findViewById(R.id.iv_news);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
