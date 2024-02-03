package kh.edu.rupp.visitme.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.visitme.databinding.ViewHolderPlaceBinding;
import kh.edu.rupp.visitme.model.Place;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    private ViewHolderPlaceBinding itemBinding;

    public PlaceViewHolder(@NonNull ViewHolderPlaceBinding itemBinding) {
        super(itemBinding.getRoot());

        this.itemBinding = itemBinding;
    }

    public void bind(Place place) {
        Picasso.get().load(place.getImageUrl()).into(itemBinding.imgPlace);
        itemBinding.txtName.setText(place.getName());
    }

}
