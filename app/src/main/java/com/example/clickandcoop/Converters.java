package com.example.clickandcoop;

import androidx.room.TypeConverter;

import java.util.UUID;

public class Converters {

    @TypeConverter
    public static String fromUUID(UUID uuid) {
        return uuid.toString();
    }

    @TypeConverter
    public static UUID uuidFromString(String string) {
        return UUID.fromString(string);
    }
}
