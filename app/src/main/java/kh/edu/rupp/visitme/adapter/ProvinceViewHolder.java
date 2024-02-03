package kh.edu.rupp.visitme.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kh.edu.rupp.visitme.databinding.ViewHolderProvinceBinding;
import kh.edu.rupp.visitme.model.Province;

public class ProvinceViewHolder extends RecyclerView.ViewHolder {

    private ViewHolderProvinceBinding binding;

    public ProvinceViewHolder(@NonNull ViewHolderProvinceBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Province province){
        binding.txtProvince.setText(province.getName());
    }

}
