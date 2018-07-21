package com.moneytap.com.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.moneytap.com.model.BaseLinkDTO;
import com.moneytap.com.model.BaseResponseDTO;
import com.moneytap.com.model.PageLink;
import com.moneytap.com.model.SearchModel;
import com.moneytap.com.network.ApiUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SearchPresenter extends MvpBasePresenter<Search.View> implements Search.Presenter {

    private Context mContext;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = SearchPresenter.class.getName();

    public SearchPresenter(Context mContext) {
        this.mContext = mContext;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchItem(String search) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "query");
        hashMap.put("format", "json");
        hashMap.put("prop", "pageimages|pageterms");
        hashMap.put("titles", search);
        hashMap.put("redirects", "1");
        hashMap.put("formatversion", "2");
        hashMap.put("piprop", "thumbnail");
        hashMap.put("pilimit", "3");
        hashMap.put("wbptterms", "description");
        compositeDisposable.add(ApiUtils.getAPIService().getSearchData(hashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Response<BaseResponseDTO<SearchModel>>>() {
                    @Override
                    public void accept(final Response<BaseResponseDTO<SearchModel>> searchResponse) throws Exception {
                        Log.e(TAG, "searchResponse==" + searchResponse.body().getQuery().getPages().get(0).getTitle());
                        if (searchResponse.body().getQuery().getPages() != null) {
                            ifViewAttached(true, new ViewAction<Search.View>() {
                                @Override
                                public void run(@NonNull Search.View view) {
                                    view.setData(searchResponse.body().getQuery());
                                }
                            });
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "throwable==" + throwable.getMessage());
                    }
                }));
    }

    @Override
    public void findPageLink(int pageId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("action", "query");
        hashMap.put("prop", "info");
        hashMap.put("pageids", String.valueOf(pageId));
        hashMap.put("inprop", "url");
        hashMap.put("format", "json");
        compositeDisposable.add(ApiUtils.getAPIService().getDataLink(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<BaseLinkDTO>>() {
                    @Override
                    public void accept(Response<BaseLinkDTO> baseLinkDTOResponse) throws Exception {
                        Log.e(TAG, "baseLinkDTOResponse==" + baseLinkDTOResponse.body().getQuery().getPages());
                        Map pages = baseLinkDTOResponse.body().getQuery().getPages();
                        Set<Integer> a = pages.keySet();
                        PageLink obj = new PageLink();
                        for (int i : a) {
                            obj = (PageLink) pages.get(i);
                        }
                        final PageLink finalObj = obj;
                        ifViewAttached(true, new ViewAction<Search.View>() {
                            @Override
                            public void run(@NonNull Search.View view) {
                                view.openWikipediaLink(finalObj.getCanonicalurl());
                            }
                        });
                        Log.e(TAG, "baseLinkDTOResponseLink" + obj.getCanonicalurl());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "throwableLink==" + throwable.getMessage());
                    }
                }));
    }
}