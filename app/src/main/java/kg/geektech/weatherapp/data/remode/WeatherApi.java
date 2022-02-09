package kg.geektech.weatherapp.data.remode;

import kg.geektech.weatherapp.data.models.MainModels;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather?")
    Call<MainModels> getApi(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String units
    );
}
