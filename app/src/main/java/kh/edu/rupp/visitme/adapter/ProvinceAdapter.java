package kh.edu.rupp.visitme.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kh.edu.rupp.visitme.databinding.ViewHolderProvinceBinding;
import kh.edu.rupp.visitme.model.Province;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceViewHolder> {

    private List<Province> dataSet = new ArrayList<>();

    public void setDataSet(List<Province> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProvinceBinding binding = ViewHolderProvinceBinding.inflate(layoutInflater, parent, false);
        return new ProvinceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceViewHolder holder, int position) {

        Province province = dataSet.get(position);
        holder.bind(province);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
