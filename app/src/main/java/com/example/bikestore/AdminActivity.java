package com.example.bikestore;


import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bikestore.db.AppDatabase;
import com.example.bikestore.db.BikeStoreDAO;


public class AdminActivity extends AppCompatActivity {
    User mUser;
    BikeStoreDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        int mUserId = getIntent().getIntExtra("UserID", -1);
        //getDatabase(); //return pointer "com.example.bikestore.user@cd50901"


        TextView textView = (TextView) findViewById(R.id.adminMenuDisplay);
        //if (mUser != null)
        textView.append(" " + mDAO.getUserByUsernameId(mUserId));
    }

    private void getDatabase() {//copied here from MainActivity //i think this just building a new DB of the type BikeStoreDAO.. not actually linking to the older one.
        mDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)  //building database for BikeStore Items.
                .allowMainThreadQueries()
                .build()
                .getBikeStoreDAO();
    }
}