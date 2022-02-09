package kg.geektech.weatherapp.data.local.convertor;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import kg.geektech.weatherapp.data.models.Sys;

public class SysConvertor {

    @TypeConverter
    public String fromMainString(Sys sys) {
        if (sys == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {
        }.getType();
        return gson.toJson(sys, type);
    }

    @TypeConverter
    public Sys fromMainString(String sysString) {
        if (sysString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {
        }.getType();
        return gson.fromJson(sysString, type);
    }


}
