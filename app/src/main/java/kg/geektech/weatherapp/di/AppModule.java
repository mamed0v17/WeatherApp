package kg.geektech.weatherapp.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kg.geektech.weatherapp.data.local.AppDatabase;
import kg.geektech.weatherapp.data.local.RoomClient;
import kg.geektech.weatherapp.data.local.WeatherDao;
import kg.geektech.weatherapp.data.remode.RetrofitClient;
import kg.geektech.weatherapp.data.remode.WeatherApi;
import kg.geektech.weatherapp.data.repositories.MainRepositories;

@Module
@InstallIn({SingletonComponent.class})
public abstract class AppModule {
    @Provides
    public static WeatherApi providerApi() {
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static MainRepositories providersRepositories(WeatherApi api, WeatherDao dao) {
        return new MainRepositories(api, dao);
    }

    @Provides
    public static AppDatabase provideRoomClient(@ApplicationContext Context context) {
        return new RoomClient().provideAppDatabase(context);
    }

    @Provides
    public static WeatherDao provideDao(AppDatabase database) {
        return database.provideDao();
    }
}
