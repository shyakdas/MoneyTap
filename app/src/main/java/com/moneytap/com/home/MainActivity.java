package com.moneytap.com.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.moneytap.com.R;
import com.moneytap.com.home.adapter.SearchAdapter;
import com.moneytap.com.model.SearchModel;
import com.moneytap.com.network.ApiUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private EditText mSearch;
    private static final String TAG = MainActivity.class.getName();
    private CompositeDisposable compositeDisposable;
    private SearchAdapter searchAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        mSearch = findViewById(R.id.search_text);
        mRecyclerView = findViewById(R.id.recylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchAdapter = new SearchAdapter(this);
        mRecyclerView.setAdapter(searchAdapter);
        loadData();
    }

    private void loadData() {
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("action", "query");
                hashMap.put("format", "json");
                hashMap.put("prop", "pageimages|pageterms");
                hashMap.put("titles", charSequence.toString());
                hashMap.put("redirects", "1");
                hashMap.put("formatversion", "2");
                hashMap.put("piprop", "thumbnail");
                hashMap.put("pilimit", "3");
                hashMap.put("wbptterms", "description");
                compositeDisposable.add(ApiUtils.getAPIService().getFollowSearchData(hashMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<SearchModel>() {
                            @Override
                            public void accept(SearchModel searchModel) throws Exception {
                                Log.e(TAG, "searchModelResponse==" + searchModel.getQuery().getPages());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "nhinHua==" + throwable.getMessage());
                            }
                        }));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}