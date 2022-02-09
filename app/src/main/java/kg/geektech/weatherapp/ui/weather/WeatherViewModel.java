package kg.geektech.weatherapp.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weatherapp.common.Resource;
import kg.geektech.weatherapp.data.models.MainModels;
import kg.geektech.weatherapp.data.remode.WeatherApi;
import kg.geektech.weatherapp.data.repositories.MainRepositories;


@HiltViewModel
public class WeatherViewModel extends ViewModel {
    private MainRepositories repositories;
    private String city;
    public LiveData<Resource<MainModels>> weatherLiveData;

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    public WeatherViewModel(MainRepositories repositories) {
        this.repositories = repositories;
    }

    public void getWeather() {
        repositories.setCity(city);
        weatherLiveData = repositories.getCharacters();
    }
}
