package com.example.clickandcoop;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Score.class, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class BDDSingleton extends RoomDatabase {
    private static final String DB_NAME = "scores_db";
    private static volatile BDDSingleton SINGLETON;

    public abstract ScoreDAO scoreDAO();

    public static BDDSingleton getInstance(Context context) {
        if (SINGLETON == null) {
            SINGLETON = Room.databaseBuilder(context.getApplicationContext(),
                    BDDSingleton.class, DB_NAME).build();
        }
        return SINGLETON;
    }
}
