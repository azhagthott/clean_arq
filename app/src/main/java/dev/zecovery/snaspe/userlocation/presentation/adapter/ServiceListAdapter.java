package dev.zecovery.snaspe.userlocation.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.userlocation.presentation.adapter.viewholder.ServiceListViewHolder;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnSelectServiceListener;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListViewHolder> {

    private List<Service> serviceList;
    private OnSelectServiceListener listener;

    public ServiceListAdapter(List<Service> serviceList, OnSelectServiceListener listener) {
        this.serviceList = serviceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_service_list_item, parent, false);
        return new ServiceListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListViewHolder holder, int position) {
        Service service = serviceList.get(position);

        holder.setServiceName(service.serviceName);
        holder.clServiceNameContainer.setOnClickListener(view -> listener.onServiceSelected(service));
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
}
