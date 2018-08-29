package com.husseinfardous.simpleandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Screen (activity_get.xml)
public class GetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        // Call the methods defined below
        getUser();
        getUserId();
        getUserIdTransfers();
        getTransferId();
        home();
    }

    // Take the user to a new screen (activity_get_response.xml).
    // Pass the given candidate parameter to this new screen for the GET /user request.
    protected void getUser() {

        final EditText candidate = (EditText) findViewById(R.id.firstCandidate);
        final Button get = (Button) findViewById(R.id.firstButton);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Candidate Parameter Input should be at least 5 characters.
                if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {
                    Intent responseIntent = new Intent(getApplicationContext(), GetResponse.class);
                    // Identifier that lets the new screen know which GET request to handle.
                    responseIntent.putExtra("Method", 1);
                    // Pass the given candidate parameter.
                    responseIntent.putExtra("Candidate", candidate.getText().toString());
                    startActivity(responseIntent);
                }
            }
        });
    }

    // Take the user to a new screen (activity_get_response.xml).
    // Pass the given user id and candidate parameter to this new screen for the GET /user/:id request.
    protected void getUserId() {

        final EditText userId = (EditText) findViewById(R.id.userId);
        final EditText candidate = (EditText) findViewById(R.id.secondCandidate);
        final Button get = (Button) findViewById(R.id.secondButton);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User ID input should be at least 1 character (positive integers only).
                if ((userId.getText().length() < 1) || (!userId.getText().toString().matches("[0-9]+"))) {
                    userId.setError("Invalid User ID");
                }
                // Candidate Parameter Input should be at least 5 characters.
                else if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {
                    Intent responseIntent = new Intent(getApplicationContext(), GetResponse.class);
                    // Identifier that lets the new screen know which GET request to handle.
                    responseIntent.putExtra("Method", 2);
                    // Pass the given user id and candidate parameter.
                    responseIntent.putExtra("User ID", userId.getText().toString());
                    responseIntent.putExtra("Candidate", candidate.getText().toString());
                    startActivity(responseIntent);
                }
            }
        });
    }

    // Take the user to a new screen (activity_get_response.xml).
    // Pass the given user id and candidate parameter to this new screen for the GET /user/:id/transfers request.
    protected void getUserIdTransfers() {

        final EditText userId = (EditText) findViewById(R.id.userId);
        final EditText candidate = (EditText) findViewById(R.id.secondCandidate);
        final Button get = (Button) findViewById(R.id.thirdButton);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User ID Input should be at least 1 character (positive integers only).
                if ((userId.getText().length() < 1) || (!userId.getText().toString().matches("[0-9]+"))) {
                    userId.setError("Invalid User ID");
                }
                // Candidate Parameter Input should be at least 5 characters.
                else if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {
                    Intent responseIntent = new Intent(getApplicationContext(), GetResponse.class);
                    // Identifier that lets the new screen know which GET request to handle.
                    responseIntent.putExtra("Method", 3);
                    // Pass the given user id and candidate parameter.
                    responseIntent.putExtra("User ID", userId.getText().toString());
                    responseIntent.putExtra("Candidate", candidate.getText().toString());
                    startActivity(responseIntent);
                }
            }
        });
    }

    // Take the user to a new screen (activity_get_response.xml).
    // Pass the given transfer id and candidate parameter to this new screen for the GET /transfer/:id request.
    protected void getTransferId() {

        final EditText transferId = (EditText) findViewById(R.id.transferId);
        final EditText candidate = (EditText) findViewById(R.id.thirdCandidate);
        final Button get = (Button) findViewById(R.id.fourthButton);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Transfer ID input should be at least 1 character (positive integers only).
                if ((transferId.getText().length() < 1) || (!transferId.getText().toString().matches("[0-9]+"))) {
                    transferId.setError("Invalid Transfer ID");
                }
                // Candidate Parameter Input should be at least 5 characters long.
                else if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {
                    Intent responseIntent = new Intent(getApplicationContext(), GetResponse.class);
                    // Identifier that lets the new screen know which GET request to handle.
                    responseIntent.putExtra("Method", 4);
                    // Pass the given transfer id and candidate parameter.
                    responseIntent.putExtra("Transfer ID", transferId.getText().toString());
                    responseIntent.putExtra("Candidate", candidate.getText().toString());
                    startActivity(responseIntent);
                }
            }
        });
    }

    // Take the user to the home screen (activity_main.xml).
    protected void home() {

        final Button home = (Button) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}