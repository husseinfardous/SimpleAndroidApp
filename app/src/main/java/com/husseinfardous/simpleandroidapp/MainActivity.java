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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// Screen (activity_get.xml): Home Screen
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call the methods defined below
        getUser();
        get();
        post();
        delete();
    }

    // Take the user to a new screen (activity_get.xml).
    // The new screen handles the four different GET requests.
    protected void get() {

        final Button button = (Button) findViewById(R.id.get);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent getIntent = new Intent(getApplicationContext(), GetActivity.class);
                startActivity(getIntent);
            }
        });
    }

    // Take the user to a new screen (activity_post.xml).
    // The new screen handles the two different POST requests.
    protected void post() {

        final Button button = (Button) findViewById(R.id.post);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent postIntent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(postIntent);
            }
        });
    }

    // Take the user to a new screen (activity_delete.xml).
    // The new screen handles the DELETE request.
    protected void delete() {

        final Button button = (Button) findViewById(R.id.delete);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent deleteIntent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(deleteIntent);
            }
        });
    }

    // Handle GET /user request for my unique candidate parameter.
    // Response to the request is used to make an ArrayList of User Names.
    // The list is displayed in the TextView at the center of the screen.
    protected void getUser() {

        String URL = "http://fake-button.herokuapp.com/user?candidate=hash42685";
        // RequestQueue moves the GET request through the network pipeline, gets it serviced,
        // and parses/delivers the request's raw response.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final TextView textView = (TextView) findViewById(R.id.users);

        // GET /user request.
        // Expected to respond with JSONArray.
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // If successful, make a list of user names and display in TextView.
                        ArrayList<String> users = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                users.add(object.getString("name"));
                            }
                            // Error Handling
                            catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("Response", "Error", e);
                            }
                        }
                        textView.setText(users.toString());
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
}