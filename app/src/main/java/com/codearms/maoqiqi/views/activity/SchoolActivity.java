package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

public class SchoolActivity extends BaseActivity {

    private AutoCompleteTextView actSchool;
    private MultiAutoCompleteTextView multiActSpecialty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.school_label) + getString(R.string.specialty_label));
        setSupportActionBar(toolbar);

        actSchool = findViewById(R.id.act_school);
        String[] schools = getResources().getStringArray(R.array.schools);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, schools);
        actSchool.setAdapter(adapter1);

        multiActSpecialty = findViewById(R.id.multi_act_specialty);
        String[] specialties = getResources().getStringArray(R.array.specialties);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, specialties);
        multiActSpecialty.setAdapter(adapter2);
        multiActSpecialty.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
