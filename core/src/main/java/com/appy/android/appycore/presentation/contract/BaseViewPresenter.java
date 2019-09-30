package com.appy.android.appycore.presentation.contract;

/**
 * Created by Francisco Barrios 19/10/2018
 */
public interface BaseViewPresenter<T> {

    /**
     * This method will be executed after
     * [AppCompatActivity.onCreate] ()} in case presenter is attached to activity
     * [Fragment.onCreateView] ()}  in case presenter is attached to fragment
     */
    void initialize(T view);
}
