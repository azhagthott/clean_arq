package dev.zecovery.snaspe.userlocation.presentation.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dev.zecovery.snaspe.R;

public class ServiceListViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout clServiceNameContainer;
    private TextView tvServiceName;

    public ServiceListViewHolder(@NonNull View view) {
        super(view);
        clServiceNameContainer = (ConstraintLayout) view;
        tvServiceName = view.findViewById(R.id.tv_service_name);
    }

    public void setServiceName(String name) {
        if (name != null) {
            tvServiceName.setText(name);
        }
    }
}
