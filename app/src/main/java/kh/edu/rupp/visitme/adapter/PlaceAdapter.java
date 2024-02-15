package kh.edu.rupp.visitme.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import kh.edu.rupp.visitme.databinding.ViewHolderPlaceBinding;
import kh.edu.rupp.visitme.model.Place;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    public PlaceAdapter(List<Place> dataSet) {
        this.dataSet = dataSet;
    }

    private List<Place> dataSet;

    private RecyclerViewItemListener listener;

    public void setListener(RecyclerViewItemListener listener) {
        this.listener = listener;
    }

    public Place getItem(int index) {
        return dataSet.get(index);
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderPlaceBinding itemBinding = ViewHolderPlaceBinding.inflate(layoutInflater, parent, false);
        return new PlaceViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        holder.bind(dataSet.get(position));
        holder.itemView.setOnClickListener( v -> {
            listener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
