package com.moneytap.com.home;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.moneytap.com.model.SearchModel;

public interface Search {

    interface View extends MvpLceView<SearchModel> {

        void openWikipediaLink(String link);
    }

    interface Presenter extends MvpPresenter<Search.View> {

        void searchItem(String search);

        void findPageLink(int pageId);

    }
}