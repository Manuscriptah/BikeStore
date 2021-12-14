package com.example.bikestore.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

//import com.example.bikestore.StoreItems; //StoreItems class
import com.example.bikestore.StoreItems;
import com.example.bikestore.User; //User class

import java.util.List;

@Dao
public interface BikeStoreDAO {//Data access object layer for room database

    @Insert
    void insert(StoreItems... storeItems);

    @Update
    void update(StoreItems... storeItems);

    @Delete
    void delete(StoreItems... storeItems);

    @Query("SELECT * FROM " + AppDatabase.STOREITEMS_TABLE)//List all store items
    List<StoreItems> getStoreItems();

    @Query("SELECT * FROM " + AppDatabase.STOREITEMS_TABLE + " WHERE mItemId = :ItemId")//List store items by item ID
    List<StoreItems> getStoreItemsbyItemID(int ItemId);

    @Query("SELECT * FROM " + AppDatabase.STOREITEMS_TABLE + " WHERE mownedby = :ownedby")//List store items by item ID
    List<StoreItems> getStoreItemsbyOwner(int ownedby);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)//List all users
    List<User> getAllusers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE +" WHERE mUsername =:username")//List all users
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE +" WHERE mUsernameID =:userId")//List all users
    User getUserByUsernameId(int userId);
}
