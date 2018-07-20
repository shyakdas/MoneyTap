package com.moneytap.com.network;

import com.moneytap.com.model.SearchModel;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("api.php")
    Observable<SearchModel> getFollowSearchData(@QueryMap HashMap<String, String> hashMap);
}