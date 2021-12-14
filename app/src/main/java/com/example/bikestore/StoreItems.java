
package com.example.bikestore;//GymLog.class

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.bikestore.db.AppDatabase;

@Entity(tableName = AppDatabase.STOREITEMS_TABLE)
public class StoreItems {

    @PrimaryKey(autoGenerate = true)
    private int mItemId;


    private String mItemname;
    private double mCost;
    private int mQuantity;

    private int mOwnedby=-1;

    public StoreItems(String itemname, double cost, int quantity) {
        this.mItemname = itemname;
        this.mCost = cost;
        this.mQuantity = quantity;


    }

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int mItemId) {
        this.mItemId = mItemId;
    }

    public String getItemname() {
        return mItemname;
    }

    public void setItemname(String mItemname) {
        this.mItemname = mItemname;
    }

    public double getCost() {
        return mCost;
    }

    public void setCost(double mCost) {
        this.mCost = mCost;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public int getOwnedby() {
        return mOwnedby;
    }

    public void setOwnedby(int mOwnedby) {
        this.mOwnedby = mOwnedby;
    }
}