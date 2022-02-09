package kg.geektech.weatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import kg.geektech.weatherapp.data.local.convertor.MainConvertor;
import kg.geektech.weatherapp.data.models.MainModels;

@Database(entities = {MainModels.class}, version = 1)
@TypeConverters({MainConvertor.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherDao provideDao();
}
