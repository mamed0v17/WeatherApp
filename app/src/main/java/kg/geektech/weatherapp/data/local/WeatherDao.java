package kg.geektech.weatherapp.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import kg.geektech.weatherapp.data.models.MainModels;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MainModels models);

    @Query("SELECT * FROM mainmodels")
    MainModels getWeather();
}
