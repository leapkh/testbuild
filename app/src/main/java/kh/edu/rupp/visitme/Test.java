package kh.edu.rupp.visitme;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import kh.edu.rupp.visitme.model.Place;

public class Test {

    private MutableLiveData<List<Place>> nearMePlacesUiState = new MutableLiveData<>();

    public MutableLiveData<List<Place>> getNearMePlacesUiState() {
        return nearMePlacesUiState;
    }

}
