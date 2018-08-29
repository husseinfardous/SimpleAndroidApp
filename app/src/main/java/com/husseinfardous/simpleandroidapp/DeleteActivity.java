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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

// Screen (activity_delete.xml)
public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        // Call the methods defined below
        delete();
        home();
    }

    // Handle DELETE /user/:id request for the given user id and candidate parameter.
    protected void delete() {

        final EditText id = (EditText) findViewById(R.id.id);
        final EditText candidate = (EditText) findViewById(R.id.candidate);
        final Button delete = (Button) findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User ID Input should be at least 1 character (positive integers only).
                if ((id.getText().length() < 1) || (!id.getText().toString().matches("[0-9]+"))) {
                    id.setError("Invalid User ID");
                }
                // Candidate Parameter Input should be at least 5 characters.
                else if (candidate.getText().length() < 5) {
                    candidate.setError("Invalid Candidate");
                }
                else {

                    // RequestQueue moves the DELETE request through the network pipeline,
                    // gets it serviced, and parses/delivers the request's raw response.
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    String userId = id.getText().toString();
                    String cand = candidate.getText().toString();
                    String URL = "http://fake-button.herokuapp.com/user/" + userId + "?candidate=" + cand;

                    // DELETE /user/:id request.
                    // Expected to respond with String.
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.DELETE,
                            URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
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
                    // Add the DELETE /user/:id request on the RequestQueue.
                    requestQueue.add(stringRequest);
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