package com.codearms.maoqiqi.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.bean.NewsListBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 新闻列表
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/23 21:08
 */
public class NewsListFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recycler_view);

        if (getActivity() == null) return;
        String json = getJson(getActivity(), "news.json");
        NewsListBean newsListBean = new Gson().fromJson(json, NewsListBean.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), newsListBean.getNewsBeans()));
    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
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
