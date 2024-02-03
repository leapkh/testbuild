package kh.edu.rupp.visitme.api;

import java.util.List;

import kh.edu.rupp.visitme.model.Place;
import kh.edu.rupp.visitme.model.Profile;
import kh.edu.rupp.visitme.model.Province;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("profile.json")
    Call<Profile> loadProfile();

    @GET("provinces.json")
    Call<List<Province>> loadProvinces();

    @GET("near-places.json")
    Call<List<Place>> loadNearMePlaces();

    @GET("places.json")
    Call<List<Place>> loadTopPlaces();

    @GET("slide-show-images.json")
    Call<List<String>> loadSlideShowImages();


}
