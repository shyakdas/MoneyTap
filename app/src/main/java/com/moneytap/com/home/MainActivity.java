package com.moneytap.com.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceActivity;
import com.moneytap.com.R;
import com.moneytap.com.home.adapter.Search;
import com.moneytap.com.home.adapter.SearchAdapter;
import com.moneytap.com.home.adapter.SearchPresenter;
import com.moneytap.com.model.SearchModel;

public class MainActivity extends MvpLceActivity<ConstraintLayout, SearchModel, Search.View,
        Search.Presenter> implements Search.View {

    private EditText mSearch;
    private static final String TAG = MainActivity.class.getName();
    private SearchAdapter searchAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearch = findViewById(R.id.search_text);
        mRecyclerView = findViewById(R.id.contentView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchAdapter = new SearchAdapter(this);
        mRecyclerView.setAdapter(searchAdapter);
        loadData(false);
    }

    @NonNull
    @Override
    public Search.Presenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage() + "";
    }

    @Override
    public void setData(SearchModel data) {
        searchAdapter.addAll(data.getPages());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.searchItem(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}