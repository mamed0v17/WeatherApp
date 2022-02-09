package kg.geektech.weatherapp.ui.weather;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.base.BaseFragment;
import kg.geektech.weatherapp.data.local.WeatherDao;
import kg.geektech.weatherapp.data.models.MainModels;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    private WeatherViewModel viewModel;
    private NavController controller;
    private WeatherFragmentArgs args;

    @Inject
    WeatherDao dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = WeatherFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObserves() {
        viewModel.weatherLiveData.observe(getViewLifecycleOwner(), mainModelsResource -> {
            switch (mainModelsResource.status) {
                case SUCCESS: {
                    setData(mainModelsResource.data);
                    setupUI(mainModelsResource.data);
                    viewBinding.progress.setVisibility(View.GONE);
                    break;
                }
                case ERROR: {
                    viewBinding.progress.setVisibility(View.GONE);
                    setData(dao.getWeather());
                    setupUI(dao.getWeather());
                    break;
                }
                case LOADING: {
                    viewBinding.progress.setVisibility(View.VISIBLE);
                    break;
                }
            }
        });

    }

    private void setData(MainModels models) {

        String location = models.getName();
        String urlImg = "https://openweathermap.org/img/wn/" + models.getWeather().get(0).getIcon() + ".png";
        String maxTemp = Math.round(models.getMain().getTempMax()) + "C";
        String wind = String.valueOf((int) Math.round(models.getWind().getSpeed()));
        String minTemp = String.valueOf((int) Math.round(models.getMain().getTempMin()));
        String humidity = String.valueOf(models.getMain().getHumidity());
        String barometr = String.valueOf(models.getMain().getPressure());
        String mainWeather = models.getWeather().get(0).getMain();
        String tempNow = String.valueOf((int) Math.round(models.getMain().getTemp()));


        viewBinding.btnMap.setText(location);
        Glide.with(requireActivity()).load(urlImg).into(viewBinding.imageCloud);
        viewBinding.tempMax.setText(maxTemp);
        viewBinding.tempMin.setText(minTemp);
        viewBinding.txtWind.setText(wind);
        viewBinding.txtWindPercent.setText(humidity);
        viewBinding.bigTemp.setText(tempNow);
        viewBinding.txtPressure.setText(barometr);
        viewBinding.txtCloud.setText(mainWeather);


    }


    private void setupUI(MainModels weather) {
        viewBinding.bigTemp.setText(weather.getMain().getTemp().toString());
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void setupViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        viewModel.setCity(args.getCity());
        viewModel.getWeather();
    }

    @Override
    protected void callRequest() {

        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        viewBinding.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.action_weatherFragment_to_searchFragment);
            }
        });
    }
}