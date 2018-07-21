package com.moneytap.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class QueryLink {

    @SerializedName("pages")
    @Expose
    private Map<Integer, PageLink> pages;

    public Map<Integer, PageLink> getPages() {
        return pages;
    }

    public void setPages(Map<Integer, PageLink> pages) {
        this.pages = pages;
    }
}
