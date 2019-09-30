package com.appy.android.appycore.presentation.contract;

/**
 * Created by Francisco Barrios 19/10/2018
 */
public interface UIProgressView {

    /**
     * Show Progress bar
     */
    void showProgress(boolean show);

    /**
     * Show Progress Message (Toast)
     */
    void showMessage(String message);
}
