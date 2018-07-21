package com.moneytap.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseLinkDTO {

    @SerializedName("batchcomplete")
    @Expose
    private String batchcomplete;
    @SerializedName("query")
    @Expose
    private QueryLink query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public QueryLink getQuery() {
        return query;
    }

    public void setQuery(QueryLink query) {
        this.query = query;
    }
}