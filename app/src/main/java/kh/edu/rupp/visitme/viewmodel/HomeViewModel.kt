package kh.edu.rupp.visitme.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.visitme.api.ApiService
import kh.edu.rupp.visitme.model.Place
import kh.edu.rupp.visitme.model.UiState
import kh.edu.rupp.visitme.model.UiStateStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomeViewModel: ViewModel() {

    private val _nearMePlacesUiState = MutableLiveData<UiState<List<Place>>>()
    val nearMePlacesUiState: LiveData<UiState<List<Place>>> get() = _nearMePlacesUiState

    private val _topPlacesUiState = MutableLiveData<UiState<List<Place>>>()
    val topPlacesUiState: LiveData<UiState<List<Place>>> get() = _topPlacesUiState

    fun loadHomeData(){
        loadHomeSlideShow()
        loadNearMePlaces()
        loadTopPlaces()
    }

    private fun loadHomeSlideShow(){

    }

    private fun loadNearMePlaces(){

        _nearMePlacesUiState.value = UiState(UiStateStatus.loading)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/apix/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create<ApiService>()

        apiService.loadNearMePlaces().enqueue(object : Callback<List<Place>> {
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                if (response.isSuccessful) {
                    _nearMePlacesUiState.value = UiState(UiStateStatus.success, data = response.body())
                } else {
                    Log.e("HomeViewModel", "Load near me place error: ${response.message()}")
                    _nearMePlacesUiState.value = UiState(UiStateStatus.error, response.message())
                }
            }

            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.e("HomeViewModel", "Load near me place error: ${t.message}")
                _nearMePlacesUiState.value = UiState(UiStateStatus.error, t.message)
            }

        })

    }

    private fun loadTopPlaces() {
        _nearMePlacesUiState.value = UiState(UiStateStatus.loading)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://smlp-pub.s3.ap-southeast-1.amazonaws.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create<ApiService>()

        apiService.loadTopPlaces().enqueue(object : Callback<List<Place>> {
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                if (response.isSuccessful) {
                    _topPlacesUiState.value = UiState(UiStateStatus.success, data = response.body())
                } else {
                    Log.e("HomeViewModel", "Load top place error: ${response.message()}")
                    _topPlacesUiState.value = UiState(UiStateStatus.error, response.message())
                }
            }

            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.e("HomeViewModel", "Load top place error: ${t.message}")
                _topPlacesUiState.value = UiState(UiStateStatus.error, t.message)
            }

        })
    }

}