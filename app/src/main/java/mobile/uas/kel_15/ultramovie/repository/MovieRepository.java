package mobile.uas.kel_15.ultramovie.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
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
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.movie.MovieListViewModel;
import mobile.uas.kel_15.ultramovie.movie.MovieViewViewModel;

public class MovieRepository  {
    Application application;
    String url = AppConfig.SERVER + "/MovieModel.php";
    ArrayList<Movie> movieList = new ArrayList<>();

    private final MutableLiveData<List<Movie>> allMovies = new MutableLiveData<>();
    private final MutableLiveData<Movie> movieData = new MutableLiveData<>();

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getAllMovies() {
        MovieListViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try {
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

                    String[] genres =  item.getString("Genres").split(",", -1);
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
        }, Throwable::printStackTrace) {
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
                JSONObject data = jsonObject.getJSONObject("data");
//                Log.d("TAG", "data length: " + jsonArray.length());

                Movie movie;

                movie = new Movie();

                movie.setId(String.valueOf(data.getInt("id_movie")));
                movie.setTitle(data.getString("nm_movie"));

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
        }, Throwable::printStackTrace) {
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
        // TODO: Implement insert function
    }

    public void update(Movie movie) {
        // TODO: Implement update function
    }

    public void delete(String movieId) {
        // TODO: implement delete function
    }
}

