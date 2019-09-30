package com.appy.android.appycore.presentation.adapter.viewholder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T, L, R, BINDER extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected final BINDER binder;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        binder = DataBindingUtil.getBinding(itemView);
    }

    public Context getContext() {
        return itemView.getContext();
    }

    public void bind(int position, T item, @Nullable L listener, @Nullable R resident ) {
    }
}
