package com.moneytap.com.home.adapter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;
import com.moneytap.com.model.SearchModel;

public interface Search {

    interface View extends MvpLceView<SearchModel> {

    }

    interface Presenter extends MvpPresenter<Search.View> {

        public void searchItem(String search);
    }
}
