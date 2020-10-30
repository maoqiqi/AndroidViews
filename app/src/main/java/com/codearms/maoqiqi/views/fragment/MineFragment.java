package com.codearms.maoqiqi.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codearms.maoqiqi.views.R;
import com.codearms.maoqiqi.views.activity.BarActivity;
import com.codearms.maoqiqi.views.activity.ExpandableListViewActivity;
import com.codearms.maoqiqi.views.activity.GalleryActivity;
import com.codearms.maoqiqi.views.activity.GridLayoutImageViewActivity;
import com.codearms.maoqiqi.views.activity.HorizontalScrollViewActivity;
import com.codearms.maoqiqi.views.activity.SlidingPaneLayoutActivity;
import com.codearms.maoqiqi.views.activity.AdapterViewAnimatorActivity;
import com.codearms.maoqiqi.views.activity.TextClockActivity;
import com.codearms.maoqiqi.views.activity.ViewPagerActivity;
import com.codearms.maoqiqi.views.activity.ViewAnimatorActivity;
import com.codearms.maoqiqi.views.activity.ViewStubActivity;
import com.codearms.maoqiqi.views.activity.ZoomControlsActivity;

/**
 * MinePage
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/21 21:03
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private int[] ids = {
            R.id.tv_text_clock, R.id.tv_bar, R.id.tv_view_stub, R.id.tv_zoom_controls,
            R.id.tv_horizontal_scroll_view, R.id.tv_view_animator, R.id.tv_gallery,
            R.id.tv_expandable_list_view, R.id.tv_adapter_view_animator, R.id.tv_grid_Layout_image_view,
            R.id.tv_sliding_pane_layout, R.id.tv_view_pager
    };
    private Class<?>[] classes = {
            TextClockActivity.class, BarActivity.class, ViewStubActivity.class, ZoomControlsActivity.class,
            HorizontalScrollViewActivity.class, ViewAnimatorActivity.class, GalleryActivity.class,
            ExpandableListViewActivity.class, AdapterViewAnimatorActivity.class, GridLayoutImageViewActivity.class,
            SlidingPaneLayoutActivity.class, ViewPagerActivity.class
    };

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        for (int i = 0; i < ids.length; i++) {
            TextView tv = rootView.findViewById(ids[i]);
            tv.setTag(i);
            tv.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag != null) {
            int i = (int) tag;
            if (classes[i] != null) {
                startActivity(new Intent(getActivity(), classes[i]));
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_mine, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }
}