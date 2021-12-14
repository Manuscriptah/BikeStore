package com.example.bikestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikestore.db.AppDatabase;
import com.example.bikestore.db.BikeStoreDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.bikestore.userIdKey";
    private static final String PREFERENCES_KEY = "com.example.bikestore.PREFERENCES_KEY";

    TextView mMainDisplay;

    Button mSearchButton;
    Button mOrderHistoryButton;
    Button mCancelOrderButton;
    Button mAdminButton;

    Button mSignOut;

    BikeStoreDAO mBikeStoreDAO;  //DAO access layer

    List<StoreItems> mStoreItems; //list to store bike store items
    private int mUserId;

    private SharedPreferences mPreferences = null;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();

        checkForUser();
        addUserToPreference(mUserId);
        loginUser(mUserId);

        TextView textView = (TextView) findViewById(R.id.Welcome);
        if (mUser != null)
            textView.append(" " +mUser.getUsername() );


        mMainDisplay = findViewById(R.id.mainMenuDisplay);

        mSearchButton = findViewById(R.id.mainSearchButton);
        mOrderHistoryButton = findViewById(R.id.mainOrderHistoryButton);
        mCancelOrderButton = findViewById(R.id.mainCancelOrderButton);
        mAdminButton = findViewById(R.id.mainAdminButton);
        if (mUser != null) {
            if (!mUser.isAdmin()) {
                mAdminButton.setVisibility(View.INVISIBLE);
            }
        }

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminintent = new Intent(MainActivity.this, AdminActivity.class);
                adminintent.putExtra("UserID", (mUser.getUsernameID()));
                startActivity(adminintent);
            }
        });

        mSignOut = findViewById(R.id.mainSignOutButton);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
    }

    private void loginUser(int userId) {
        mUser = mBikeStoreDAO.getUserByUsernameId(userId);
        invalidateOptionsMenu();
        addUserToPreference(userId);
    }

    //savesession
    private void addUserToPreference(int userId) {
        if(mPreferences == null){
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);

        //add shit here
    }


    private void getDatabase() {
        mBikeStoreDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)  //building database for BikeStore Items.
                .allowMainThreadQueries()
                .build()
                .getBikeStoreDAO();
    }



    private void checkForUser() {
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        if(mUserId != -1){
            return;
        }

        if(mPreferences == null) {
            getPrefs();
        }
              mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }

        List<User> users = mBikeStoreDAO.getAllusers();
        if(users.size() <= 0){
            User defaultUser = new User("testuser1", "testuser1");
            mBikeStoreDAO.insert(defaultUser);
            User adminUser = new User("admin2", "admin2");
            adminUser.setAdmin(true);
            mBikeStoreDAO.insert(adminUser);
        }

        Intent intent = LoginActivity.intentFactory(this);
        startActivity(intent);

    }

    //Session Management
    private void getPrefs(){
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    private void logoutUser() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

        alertBuilder.setMessage("Logout " +mUser.getUsername() +"?");
        alertBuilder.setPositiveButton(getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        clearUserFromIntent();
                        clearUserFromPref();
                        mUserId = -1;
                        checkForUser();
                    }
                });
        alertBuilder.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
        alertBuilder.create().show();
    }

    private void clearUserFromIntent(){
        Toast.makeText(this, "logged out " +mUser.getUsername(), Toast.LENGTH_SHORT).show();
        getIntent().putExtra(USER_ID_KEY, -1);
    }
    private void clearUserFromPref() {
        addUserToPreference(-1);
    }

    public static Intent intentFactory(Context applicationContext, int usernameID) {
        Intent intent = new Intent(applicationContext, MainActivity.class);
        intent.putExtra(USER_ID_KEY,  usernameID);

        return intent;
    }
}