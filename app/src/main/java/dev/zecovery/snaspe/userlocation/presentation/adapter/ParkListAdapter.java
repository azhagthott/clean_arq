package dev.zecovery.snaspe.userlocation.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.userlocation.presentation.adapter.viewholder.ParkListViewHolder;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnSelectParkListener;

public class ParkListAdapter extends RecyclerView.Adapter<ParkListViewHolder> {

    private List<Park> parkList;
    private OnSelectParkListener listener;

    public ParkListAdapter(List<Park> parkList, OnSelectParkListener listener) {
        this.parkList = parkList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ParkListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_park_list_item, parent, false);
        return new ParkListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkListViewHolder holder, int position) {
        Park park = parkList.get(position);

        holder.setParkName(park.parkName);
        holder.clParkNameContainer.setOnClickListener(view -> listener.onParkSelected(park));
    }

    @Override
    public int getItemCount() {
        return parkList.size();
    }
}
