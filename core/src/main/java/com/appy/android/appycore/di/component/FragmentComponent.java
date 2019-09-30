package com.appy.android.appycore.di.component;

import androidx.fragment.app.Fragment;

public interface FragmentComponent<T extends Fragment> {
    /**
     * Indica que T requiere inyección desde este componente
     *
     * @param target T
     */
    void inject(T target);
}