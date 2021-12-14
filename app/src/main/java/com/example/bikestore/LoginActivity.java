package com.example.bikestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bikestore.db.AppDatabase;
import com.example.bikestore.db.BikeStoreDAO;

import java.text.BreakIterator;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private TextView mMainDisplay;

    private Button mButton;

    private BikeStoreDAO mBikeStoreDAO;

    private String mUsername;
    private String mPassword;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireupDisplay();

        getDatabase();

    }

    private void wireupDisplay(){
        mUsernameField = findViewById(R.id.editTextLoginUserName);
        mPasswordField = findViewById(R.id.editTextLoginPassword);

        mButton = findViewById(R.id.buttonLogin);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if (checkForUserInDatabase()) {
                    if (!validatePassword()) {
                        Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = MainActivity.intentFactory(getApplicationContext(), mUser.getUsernameID());
                        startActivity(intent);
                    }
                };
            }
        });
    }

    private boolean validatePassword(){
        return mUser.getPassword().equals(mPassword);
    }

    private boolean checkForUserInDatabase() {
        mUser = mBikeStoreDAO.getUserByUsername(mUsername);
        if (mUser == null) {
            Toast.makeText(this, "no user " +mUsername + " found", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void getValuesFromDisplay() {
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();

    }



    private void getDatabase(){
        mBikeStoreDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)  //building database for BikeStore Items.
                .allowMainThreadQueries()
                .build()
                .getBikeStoreDAO();

    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity.class);

        return intent;
    }
}