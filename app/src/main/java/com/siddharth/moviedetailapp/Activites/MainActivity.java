package com.siddharth.moviedetailapp.Activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.siddharth.moviedetailapp.Data.RecyclerViewAdapter;
import com.siddharth.moviedetailapp.Model.Movie;
import com.siddharth.moviedetailapp.R;
import com.siddharth.moviedetailapp.Util.Constants;
import com.siddharth.moviedetailapp.Util.Search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    RequestQueue queue;
    List<Movie> movieList;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieList = new ArrayList<>();


        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, movieList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


        queue = Volley.newRequestQueue(this);


        //Call Shared prefrence for search term..
        Search search = new Search(this);
        String mySearch = search.getSearch();

        //CALLING METHOD...
        movieList = GetallMovie(mySearch);

        // Calling Search Dialog....
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SearchDialog();

            }
        });
    }

    // Get Fetch All Movie List..
    public List<Movie> GetallMovie(String SearchTerm) {

        movieList.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL_LEFT + SearchTerm + Constants.API_KEY + Constants.URL_RIGHT, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray movieArray = response.getJSONArray("Search");
                    for (int i = 0; i < movieArray.length(); i++) {

                        JSONObject movieObject = movieArray.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("Title"));
                        movie.setYear(movieObject.getString("Year"));
                        movie.setMovietype(movieObject.getString("Type"));
                        movie.setPoster(movieObject.getString("Poster"));
                        movie.setImdbid(movieObject.getString("imdbID"));

                        movieList.add(movie);

                    }

                    // After getting things are changed so we need to upgrade or notify these changes..

                    recyclerViewAdapter.notifyDataSetChanged();//Important


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Opps Not Found!!!", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(jsonObjectRequest);

        return movieList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            //Calling Method..
            SearchDialog();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: Search Alert Dialog...
    public void SearchDialog() {

        builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.search_dialog, null);

        // setting layout..
        final EditText adsearch = view.findViewById(R.id.adsearch);
        Button adsubmit = view.findViewById(R.id.adsubmit);

        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();

        // Search button click..
        adsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Search search = new Search(MainActivity.this);

                if (!adsearch.getText().toString().isEmpty()) {

                    String mysearch = adsearch.getText().toString();
                    search.SetSearch(mysearch);

                    movieList.clear();

                    GetallMovie(mysearch);
                    if (movieList.size() < 0) {
                        Toast.makeText(MainActivity.this,
                                "No data found", Toast.LENGTH_SHORT).show();
                    } else {
                        // Change result after click on search ..
                        recyclerViewAdapter.notifyDataSetChanged();
                    }


                }
                alertDialog.dismiss();
            }
        });


    }
}
