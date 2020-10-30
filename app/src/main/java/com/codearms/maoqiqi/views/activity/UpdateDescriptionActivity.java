package com.codearms.maoqiqi.views.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新说明
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/20 17:20
 */
public class UpdateDescriptionActivity extends BaseActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_description);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.update_description);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.list_view);

        String[] versions = getResources().getStringArray(R.array.versions);
        int[] versionLogs = {R.array.version_1_0_0_logs, R.array.version_1_0_0_logs};

        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < versions.length; i++) {
            map.put(String.valueOf(list.size()), versions[i]);
            list.addAll(Arrays.asList(getResources().getStringArray(versionLogs[i])));
        }

        MyAdapter adapter = new MyAdapter(this, list, map);
        listView.setAdapter(adapter);
    }

    private final class MyAdapter extends BaseAdapter {

        private Context context;
        private List<String> data;
        private Map<String, String> map;

        MyAdapter(Context context, List<String> data, Map<String, String> map) {
            this.context = context;
            this.data = data;
            this.map = map;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public String getItem(int position) {
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
                convertView = View.inflate(context, R.layout.item_update_log, null);
                holder = new ViewHolder();
                holder.tvTitle = convertView.findViewById(R.id.tv_title);
                holder.viewLineTitle = convertView.findViewById(R.id.view_line_title);
                holder.tvContent = convertView.findViewById(R.id.tv_content);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvTitle.setVisibility(View.GONE);
            holder.viewLineTitle.setVisibility(View.GONE);
            if (map.containsKey(String.valueOf(position))) {
                holder.tvTitle.setVisibility(View.VISIBLE);
                holder.viewLineTitle.setVisibility(View.VISIBLE);
                holder.tvTitle.setText(map.get(String.valueOf(position)));
            }
            holder.tvContent.setText(getItem(position));
            return convertView;
        }
    }

    private final class ViewHolder {
        TextView tvTitle;
        View viewLineTitle;
        TextView tvContent;
    }
}
