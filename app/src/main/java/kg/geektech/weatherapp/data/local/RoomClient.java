package kg.geektech.weatherapp.data.local;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {

    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .allowMainThreadQueries().build();
    }
}
