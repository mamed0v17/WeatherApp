package kg.geektech.weatherapp.data.local.convertor;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.Coord;

public class CoordConvertor {
    @TypeConverter
    public String fromMainString(Coord coord) {
        if (coord == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {
        }.getType();
        return gson.toJson(coord, type);
    }

    @TypeConverter
    public Coord fromMainString(String mainString) {
        if (mainString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {
        }.getType();
        return gson.fromJson(mainString, type);
    }
}
