package com.codearms.maoqiqi.views.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.codearms.maoqiqi.views.BaseActivity;
import com.codearms.maoqiqi.views.R;

public class SchoolActivity extends BaseActivity {

    private AutoCompleteTextView actSchool;
    private AppCompatAutoCompleteTextView appCompatActSchool;
    private MultiAutoCompleteTextView multiActSpecialty;
    private AppCompatMultiAutoCompleteTextView appCompatMultiActSpecialty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.school_label) + getString(R.string.specialty_label));
        setSupportActionBar(toolbar);

        actSchool = findViewById(R.id.act_school);
        appCompatActSchool = findViewById(R.id.app_compat_act_school);
        multiActSpecialty = findViewById(R.id.multi_act_specialty);
        appCompatMultiActSpecialty = findViewById(R.id.app_compat_multi_act_specialty);

        String[] schools = getResources().getStringArray(R.array.schools);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, schools);
        actSchool.setAdapter(adapter1);
        appCompatActSchool.setAdapter(adapter1);

        String[] specialties = getResources().getStringArray(R.array.specialties);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.item_text, R.id.tv, specialties);
        multiActSpecialty.setAdapter(adapter2);
        multiActSpecialty.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        appCompatMultiActSpecialty.setAdapter(adapter2);
        appCompatMultiActSpecialty.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
