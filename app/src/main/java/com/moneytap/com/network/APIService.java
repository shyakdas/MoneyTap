package com.moneytap.com.network;

import com.moneytap.com.model.BaseLinkDTO;
import com.moneytap.com.model.BaseResponseDTO;
import com.moneytap.com.model.SearchModel;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("api.php")
    Observable<Response<BaseResponseDTO<SearchModel>>> getSearchData(@QueryMap HashMap<String, String> hashMap);

    @GET("api.php")
    Observable<Response<BaseLinkDTO>> getDataLink(@QueryMap HashMap<String, String> hashMap);
}