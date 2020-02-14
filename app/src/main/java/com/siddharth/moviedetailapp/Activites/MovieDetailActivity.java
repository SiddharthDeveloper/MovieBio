package com.siddharth.moviedetailapp.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.siddharth.moviedetailapp.Model.Movie;
import com.siddharth.moviedetailapp.R;
import com.siddharth.moviedetailapp.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MovieDetailActivity extends AppCompatActivity {

    TextView dettitle, detyear, detdirector, detactor, detcategory, detrating, detwriters, detplot, detboxoffice, dettime;
    Movie movie;
    ImageView detimage;
    RequestQueue queue;
    String movieid; // to get movie id.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        dettitle = findViewById(R.id.detmovietitle);
        detyear = findViewById(R.id.detrelease);
        detdirector = findViewById(R.id.detdirector);
        detactor = findViewById(R.id.detactors);
        detcategory = findViewById(R.id.detcategory);
        detrating = findViewById(R.id.detrating);
        detwriters = findViewById(R.id.detwriter);
        detplot = findViewById(R.id.detplot);
        detboxoffice = findViewById(R.id.detboxoffice);
        dettime = findViewById(R.id.dettime);
        detimage = findViewById(R.id.detmovieimage);

        queue = Volley.newRequestQueue(this);

        movie = (Movie) getIntent().getSerializableExtra("movie");

        // with the help of id go  to next screen detail screen ..
        movieid = movie.getImdbid();

        // Calling Method..
        GetMovieDetails(movieid);


    }

    // Used to fetch movie details..
    private void GetMovieDetails(String movieid) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL + movieid + Constants.API_KEY, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // bcz some movie has rating and some has not so check if it has rating then get ratings...
                if (response.has("Ratings")) {
                    try {
                        JSONArray ratings = response.getJSONArray("Ratings");

                        String source = null;
                        String value = null;

                        if (ratings.length() > 0) {

                            JSONObject myRating = ratings.getJSONObject(ratings.length() - 1);
                            source = myRating.getString("Source");
                            value = myRating.getString("Value");
                            detrating.setText(source + ":" + value);

                        } else {

                            detrating.setText("Ratings:N/A");
                        }
                        // we use response because its direct string after objectRequest..
                        dettitle.setText(response.getString("Title"));
                        detyear.setText("Released: " + response.getString("Released"));
                        detdirector.setText("Director: " + response.getString("Director"));
                        detwriters.setText("Writers: " + response.getString("Writer"));
                        detplot.setText("Plot: " + response.getString("Plot"));
                        dettime.setText("Runtime: " + response.getString("Runtime"));
                        detactor.setText("Actors: " + response.getString("Actors"));
                        detboxoffice.setText("Box Office:" + response.getString("BoxOffice"));

                        // To set Image...
                        Picasso.with(getApplicationContext())
                                .load(response.getString("Poster"))
                                .into(detimage);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MovieDetailActivity.this, "Failed!!", Toast.LENGTH_SHORT).show();

            }
        });
        queue.add(jsonObjectRequest);


    }
}
