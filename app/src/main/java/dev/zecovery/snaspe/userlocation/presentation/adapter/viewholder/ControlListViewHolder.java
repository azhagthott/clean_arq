package dev.zecovery.snaspe.userlocation.presentation.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import dev.zecovery.snaspe.R;

public class ControlListViewHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout clControlNameContainer;
    private TextView tvControlName;

    public ControlListViewHolder(@NonNull View view) {
        super(view);
        clControlNameContainer = (ConstraintLayout) view;
        tvControlName = view.findViewById(R.id.tv_control_name);
    }

    public void setControlName(String name) {
        if (name != null) {
            tvControlName.setText(name);
        }
    }
}
