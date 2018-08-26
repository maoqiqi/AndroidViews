package com.codearms.maoqiqi.views.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codearms.maoqiqi.views.R;

/**
 * MinePage
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/21 21:03
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    public TextView tvImageButton;
    public TextView tvProgressBar;
    public TextView tvSpinner;
    public TextView tvGridLayout;
    public TextView tvSlidingPaneLayout;

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
        tvImageButton = rootView.findViewById(R.id.tv_image_button);
        tvProgressBar = rootView.findViewById(R.id.tv_progress_bar);
        tvSpinner = rootView.findViewById(R.id.tv_spinner);
        tvGridLayout = rootView.findViewById(R.id.tv_grid_layout);
        tvSlidingPaneLayout = rootView.findViewById(R.id.tv_sliding_pane_layout);

        tvImageButton.setOnClickListener(this);
        tvProgressBar.setOnClickListener(this);
        tvSpinner.setOnClickListener(this);
        tvGridLayout.setOnClickListener(this);
        tvSlidingPaneLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

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
