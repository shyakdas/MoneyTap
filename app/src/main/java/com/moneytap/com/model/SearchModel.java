package com.moneytap.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModel {
    @SerializedName("pages")
    @Expose
    private List<Page> pages = null;

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public static class Page {
        @SerializedName("pageid")
        @Expose
        private Integer pageid;
        @SerializedName("ns")
        @Expose
        private Integer ns;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("thumbnail")
        @Expose
        private Terms.Thumbnail thumbnail;
        @SerializedName("terms")
        @Expose
        private Terms terms;

        public Integer getPageid() {
            return pageid;
        }

        public void setPageid(Integer pageid) {
            this.pageid = pageid;
        }

        public Integer getNs() {
            return ns;
        }

        public void setNs(Integer ns) {
            this.ns = ns;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Terms.Thumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Terms.Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public Terms getTerms() {
            return terms;
        }

        public void setTerms(Terms terms) {
            this.terms = terms;
        }

        public static class Terms {
            @SerializedName("description")
            @Expose
            private List<String> description = null;

            public List<String> getDescription() {
                return description;
            }

            public void setDescription(List<String> description) {
                this.description = description;
            }

            public static class Thumbnail {
                @SerializedName("source")
                @Expose
                private String source;
                @SerializedName("width")
                @Expose
                private Integer width;
                @SerializedName("height")
                @Expose
                private Integer height;

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }
            }
        }
    }
}