package dev.zecovery.snaspe.userlocation.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.userlocation.presentation.adapter.viewholder.ControlListViewHolder;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnSelectControlListener;

public class ControlListAdapter extends RecyclerView.Adapter<ControlListViewHolder> {

    private List<Control> controlList;
    private OnSelectControlListener listener;

    public ControlListAdapter(List<Control> controlList, OnSelectControlListener listener) {
        this.controlList = controlList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ControlListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_control_list_item, parent, false);
        return new ControlListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ControlListViewHolder holder, int position) {
        Control control = controlList.get(position);

        holder.setControlName(control.controlName);
        holder.clControlNameContainer.setOnClickListener(view -> listener.onControlSelected(control));
    }

    @Override
    public int getItemCount() {
        return controlList.size();
    }
}
