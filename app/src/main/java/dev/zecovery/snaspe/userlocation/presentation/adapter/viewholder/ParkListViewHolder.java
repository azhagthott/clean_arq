package dev.zecovery.snaspe.userlocation.presentation.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dev.zecovery.snaspe.R;

public class ParkListViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout clParkNameContainer;
    private TextView tvParkName;

    public ParkListViewHolder(@NonNull View view) {
        super(view);
        clParkNameContainer = (ConstraintLayout) view;
        tvParkName = view.findViewById(R.id.tv_park_name);
    }

    public void setParkName(String name) {
        if (name != null) {
            tvParkName.setText(name);
        }
    }
}
