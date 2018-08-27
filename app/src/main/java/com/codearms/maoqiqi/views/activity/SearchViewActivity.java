package com.codearms.maoqiqi.views.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import java.lang.reflect.Method;

public class SearchViewActivity extends BaseActivity {

    private SearchView searchView;
    private SearchView.SearchAutoComplete searchAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.search);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // 找到searchView
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) searchItem.getActionView();
        // 设置默认无内容时的文字提示
        searchView.setQueryHint("输入歌曲名查找");
        // 当展开无输入内容的时候,没有关闭的图标
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        // 设置提示文字颜色
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(this, R.color.white));
        // 设置内容文字颜色
        searchAutoComplete.setTextColor(ContextCompat.getColor(this, R.color.white));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (searchAutoComplete.isShown()) {
            try {
                // 清除文本
                searchAutoComplete.setText("");
                // 利用反射调用收起SearchView的onCloseClicked()方法
                Method method = searchView.getClass().getDeclaredMethod("onCloseClicked");
                method.setAccessible(true);
                method.invoke(searchView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.onBackPressed();
        }
    }
}
