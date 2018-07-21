package com.moneytap.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchLink {

    @SerializedName("pages")
    @Expose
    private Pages pages;

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public static class Pages {

        @SerializedName("2150841")
        @Expose
        private PageLink pageLink;

        public PageLink get736() {
            return pageLink;
        }

        public void set736(PageLink pageLink) {
            this.pageLink = pageLink;
        }
    }
}