package com.example.bikestore.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bikestore.StoreItems;
import com.example.bikestore.User;

@Database(entities = {StoreItems.class, User.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase{
    public static final String DB_NAME = "BIKESTORE_DATABASE";
    public static final String STOREITEMS_TABLE = "BIKESTORE_TABLE";
    public static final String USER_TABLE = "USER_TABLE";

    public abstract BikeStoreDAO getBikeStoreDAO();
    //public abstract BikeStoreDAO getUsersDAO();



}
