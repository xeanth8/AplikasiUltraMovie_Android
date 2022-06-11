package mobile.uas.kel_15.ultramovie.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.uas.kel_15.ultramovie.AppConfig;
import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.movie.MovieFillViewModel;
import mobile.uas.kel_15.ultramovie.movie.MovieListViewModel;
import mobile.uas.kel_15.ultramovie.movie.MovieViewViewModel;

public class MovieRepository  {
    // Untuk mendapat context aplikasi karena Volley membutuhkan
    Application application;
    String url = AppConfig.SERVER + "/MovieModel.php";

    // Data dari database masuk ke sini sebelum ke MutableLiveData
    ArrayList<Movie> movieList = new ArrayList<>();

    // MutableLiveData untuk semua dan satu movie
    private final MutableLiveData<List<Movie>> allMovies = new MutableLiveData<>();
    private final MutableLiveData<Movie> movieData = new MutableLiveData<>();

    public MovieRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Movie>> getAllMovies() {
        // Munculkan loading spinner
        MovieListViewModel.processStarted();

        // Proses standar request dengan Volley
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try {
                // Hilangkan laoding spinner
                MovieListViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Log.d("TAG", "data length: " + jsonArray.length());

                Movie movie;
                movieList.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    movie = new Movie();

                    movie.setId(String.valueOf(item.getInt("id_movie")));
                    movie.setTitle(item.getString("nm_movie"));
                    movie.setDirector(item.getString("director"));

                    String[] genres = item.getString("Genres").split(",", -1);
                    movie.setGenres(genres);

                    String[] writers = item.getString("Writers").split(",", -1);
                    movie.setWriters(writers);

                    String[] stars = item.getString("stars").split(",", -1);
                    movie.setStars(stars);

                    movie.setSynopsis(item.getString("sinopsis"));

                    movieList.add(movie);
                }

                allMovies.postValue(movieList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "read");

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);

        return allMovies;
    }

    public MutableLiveData<Movie> getMovie(String id) {
        MovieViewViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try {
                MovieViewViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);

                // Di getAllMovie, ini jadi Array karena banyak data. Ini jadi Object karena hanya 1.
                JSONObject data = jsonObject.getJSONObject("data");

                Movie movie;

                movie = new Movie();

                movie.setId(String.valueOf(data.getInt("id_movie")));
                movie.setTitle(data.getString("nm_movie"));
                movie.setDirector(data.getString("director"));

                String[] genres =  data.getString("Genres").split(",", -1);
                movie.setGenres(genres);

                String[] writers = data.getString("Writers").split(",", -1);
                movie.setWriters(writers);

                String[] stars = data.getString("stars").split(",", -1);
                movie.setStars(stars);

                movie.setSynopsis(data.getString("sinopsis"));

                movieData.postValue(movie);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "find");
                params.put("id_movie", id);

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);

        return movieData;
    }

    public void insert(Movie movie) {
        MovieFillViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            MovieFillViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "create");
                params.put("nm_movie", movie.getTitle());
                params.put("genres", String.join(",", movie.getGenres()));
                params.put("writers", String.join(",", movie.getWriters()));
                params.put("director", movie.getDirector());
                params.put("stars", String.join(",", movie.getStars()));
                params.put("sinopsis", movie.getSynopsis());

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }

    public void update(Movie movie) {
        MovieFillViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            MovieFillViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "update");
                params.put("id_movie", movie.getId());
                params.put("nm_movie", movie.getTitle());
                params.put("genres", String.join(",", movie.getGenres()));
                params.put("writers", String.join(",", movie.getWriters()));
                params.put("director", movie.getDirector());
                params.put("stars", String.join(",", movie.getStars()));
                params.put("sinopsis", movie.getSynopsis());

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }

    public void delete(String movieId) {
        MovieViewViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            MovieViewViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "delete");
                params.put("id_movie", movieId);

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }
}

