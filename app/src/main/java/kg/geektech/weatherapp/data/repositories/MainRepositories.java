package kg.geektech.weatherapp.data.repositories;

import androidx.lifecycle.MutableLiveData;

import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.local.WeatherDao;
import kg.geektech.weatherapp.data.models.MainModels;
import kg.geektech.weatherapp.data.remode.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositories {

    private WeatherApi api;
    private String city;
    private WeatherDao dao;

    public MainRepositories(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public MutableLiveData<Resource<MainModels>> getCharacters() {
        MutableLiveData<Resource<MainModels>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getApi(city, "34b99b60a3c4928e035b29be4e9ee015", "metric").enqueue(new Callback<MainModels>() {
            @Override
            public void onResponse(Call<MainModels> call, Response<MainModels> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                    dao.getWeather();
                }
            }

            @Override
            public void onFailure(Call<MainModels> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                dao.getWeather();
            }
        });
        return liveData;
    }


}
