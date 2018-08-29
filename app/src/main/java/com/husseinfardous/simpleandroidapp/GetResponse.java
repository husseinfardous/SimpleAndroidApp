package com.husseinfardous.simpleandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

// Screen (activity_get_response.xml)
public class GetResponse extends AppCompatActivity {

    // RequestQueue moves the GET requests through the network pipeline, gets them serviced,
    // and parses/delivers the requests' raw responses.
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_response);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Retrieve passed identifier from (GetActivity.java).
        int method = getIntent().getIntExtra("Method", 0);

        // Handle GET /user request.
        if (method == 1) {
            getUser();
        }
        // Handle GET /user/:id request.
        else if (method == 2) {
            getUserId();
        }
        // Handle GET /user/:id/transfers request.
        else if (method == 3) {
            getUserIdTransfers();
        }
        // Handle GET /transfer/:id request.
        else {
            getTransferId();
        }

        // Call the methods defined below.
        back();
        home();
    }

    // Handle GET /user request for the given candidate parameter.
    // The response to the request is displayed in the TextView at the center of the screen.
    protected void getUser() {

        // Retrieve passed candidate parameter from (GetActivity.java).
        String candidate = getIntent().getStringExtra("Candidate");
        String URL = "http://fake-button.herokuapp.com/user?candidate=" + candidate;

        final TextView textView = (TextView) findViewById(R.id.responseData);

        // GET /user request.
        // Expected to respond with JSONArray.
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Successful.
                        textView.setText(response.toString());
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
        // Add the GET /user request on the RequestQueue.
        requestQueue.add(arrayRequest);
    }

    // Handle GET /user/:id request for the given user id and candidate parameter.
    // The response to the request is displayed in the TextView at the center of the screen.
    protected void getUserId() {

        // Retrieve passed user id and candidate parameter from (GetActivity.java).
        String userId = getIntent().getStringExtra("User ID");
        String candidate = getIntent().getStringExtra("Candidate");
        String URL = "http://fake-button.herokuapp.com/user/" + userId + "?candidate=" + candidate;

        final TextView textView = (TextView) findViewById(R.id.responseData);

        // GET /user/:id request.
        // Expected to respond with JSONObject.
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Successful.
                        textView.setText(response.toString());
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
        // Add the GET /user/:id request on the RequestQueue.
        requestQueue.add(objectRequest);
    }

    // Handle GET /user/:id/transfers request for the given user id and candidate parameter.
    // The response to the request is displayed in the TextView at the center of the screen.
    protected void getUserIdTransfers() {

        // Retrieve passed user id and candidate parameter from (GetActivity.java).
        String userId = getIntent().getStringExtra("User ID");
        String candidate = getIntent().getStringExtra("Candidate");
        String URL = "http://fake-button.herokuapp.com/user/" + userId + "/transfers?candidate=" + candidate;

        final TextView textView = (TextView) findViewById(R.id.responseData);

        // GET /user/:id/transfers request.
        // Expected to respond with JSONArray.
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Successful.
                        textView.setText(response.toString());
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
        // Add the GET /user/:id/transfers request on the RequestQueue.
        requestQueue.add(arrayRequest);
    }

    // Handle GET /transfer/:id request for the given transfer id and candidate parameter.
    // The response to the request is displayed in the TextView at the center of the screen.
    protected void getTransferId() {

        // Retrieve passed transfer id and candidate parameter from (GetActivity.java).
        String transferId = getIntent().getStringExtra("Transfer ID");
        String candidate = getIntent().getStringExtra("Candidate");
        String URL = "http://fake-button.herokuapp.com/transfer/" + transferId + "?candidate=" + candidate;

        final TextView textView = (TextView) findViewById(R.id.responseData);

        // GET /transfer/:id request.
        // Expected to respond with JSONObject.
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Successful.
                        textView.setText(response.toString());
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
        // Add the GET /transfer/:id request on the RequestQueue.
        requestQueue.add(objectRequest);
    }

    // Take the user back to the previous screen (activity_get.xml).
    protected void back() {

        final Button back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(getApplicationContext(), GetActivity.class);
                startActivity(getIntent);
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