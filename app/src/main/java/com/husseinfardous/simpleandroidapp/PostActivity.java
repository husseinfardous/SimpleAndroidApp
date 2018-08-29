package com.husseinfardous.simpleandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

// Screen (activity_post.xml)
public class PostActivity extends AppCompatActivity {

    // RequestQueue moves the POST requests through the network pipeline, gets them serviced,
    // and parses/delivers the requests' raw responses.
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Call the methods defined below
        postUser();
        postTransfer();

        // Take the user to the home screen (activity_main.xml).
        final Button home = (Button) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    // Handle POST /user request for the given name, email, and candidate parameter.
    protected void postUser() {

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText candidate = (EditText) findViewById(R.id.userCandidate);
        final Button postUser = (Button) findViewById(R.id.postUser);

        postUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Name Input should be at least 5 characters (letters and spaces only).
                if ((name.getText().length() < 5) || (!name.getText().toString().matches("^[ A-Za-z]+$"))) {
                    name.setError("Invalid Name");
                }
                // Email Input should be at least 15 characters (two of which are '@' and '.').
                else if ((email.getText().length() < 15) || (!email.getText().toString().contains("@"))
                        || (!email.getText().toString().contains("."))) {
                    email.setError("Invalid Email");
                }
                // Candidate Parameter Input should be at least 5 characters.
                else if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {

                    String userName = name.getText().toString();
                    String userEmail = email.getText().toString();
                    String userCandidate = candidate.getText().toString();

                    // JSONObject to be given with POST request.
                    JSONObject object = new JSONObject();

                    try {
                        object.put("name", userName);
                        object.put("email", userEmail);
                        object.put("candidate", userCandidate);
                    }
                    // Error Handling.
                    catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Response", "Error", e);
                    }

                    String URL = "http://fake-button.herokuapp.com/user";

                    // POST /user request.
                    // Expected to respond with JSONObject.
                    JsonObjectRequest objectRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            URL,
                            object,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Successful.
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Unsuccessful.
                                    error.printStackTrace();
                                    Log.e("Response", "Error", error);
                                }
                            }
                    );
                    // Add the POST /user request on the RequestQueue.
                    requestQueue.add(objectRequest);
                }
            }
        });
    }

    // Handle POST /transfer request for given amount, user id, and candidate parameter.
    protected void postTransfer() {

        final EditText amount = (EditText) findViewById(R.id.amount);
        final EditText id = (EditText) findViewById(R.id.id);
        final EditText candidate = (EditText) findViewById(R.id.transferCandidate);
        final Button postTransfer = (Button) findViewById(R.id.postTransfer);

        postTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Amount Input should be at least 1 character (positive and negative integers only).
                if ((amount.getText().length() < 1)
                        || ((amount.getText().toString().charAt(0) != '-') &&
                                (!amount.getText().toString().matches("[0-9]+")))
                        || ((amount.getText().toString().charAt(0) == '-') &&
                                (!amount.getText().toString().substring(1).matches("[0-9]+")))) {
                    amount.setError("Invalid Amount");
                }
                // User ID Input should be at least 1 character (positive integers only).
                else if ((id.getText().length() < 1) || (!id.getText().toString().matches("[0-9]+"))) {
                    id.setError("Invalid User ID");
                }
                // Candidate Parameter Input should be at least 5 characters.
                else if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {

                    String transferAmount = amount.getText().toString();
                    String transferId = id.getText().toString();
                    String transferCandidate = candidate.getText().toString();

                    // JSONObject to be given with POST request.
                    JSONObject object = new JSONObject();

                    try {
                        object.put("amount", transferAmount);
                        object.put("user_id", Integer.parseInt(transferId));
                        object.put("candidate", transferCandidate);
                    }
                    // Error Handling
                    catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Response", "Error", e);
                    }

                    String URL = "http://fake-button.herokuapp.com/transfer";

                    // POST /transfer request.
                    // Expected to respond with JSONObject.
                    JsonObjectRequest objectRequest = new JsonObjectRequest(
                            Request.Method.POST,
                            URL,
                            object,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Successful.
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Unsuccessful.
                                    error.printStackTrace();
                                    Log.e("Response", "Error", error);
                                }
                            }
                    );
                    // Add the POST /transfer request on the RequestQueue.
                    requestQueue.add(objectRequest);
                }
            }
        });
    }
}