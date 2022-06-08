package mobile.uas.kel_15.ultramovie.movie;

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
import java.util.List;

import mobile.uas.kel_15.ultramovie.NetworkManager;
import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.MoviesDummy;
import mobile.uas.kel_15.ultramovie.model.Movie;

public class MovieRepository {
    static ArrayList<Movie> movieList;
    private final MutableLiveData<List<Movie>> allMovies = new MutableLiveData<>();

    public MovieRepository(Application application) {
        movieList = new ArrayList<>();
        for(int i = 0; i < MoviesDummy.titles.length; i++) {
            movieList.add(new Movie(
                    MoviesDummy.titles[i],
                    MoviesDummy.genres[i],
                    MoviesDummy.writers[i]
            ));
        }

        allMovies.postValue(movieList);
    }

    public MutableLiveData<List<Movie>> getAllMovies() {
        String url = NetworkManager.SERVER + "/movie.php";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                responses -> {
                    try {
                        JSONObject response = new JSONObject(responses);
//                        Toast.makeText(ViewData.this, response.getString("error_text"), Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = response.getJSONArray("data");
                        Log.d("TAG", "data length: " + jsonArray.length());

                        Movie movie;
                        movieList.clear();

                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject item = jsonArray.getJSONObject(i);

                            movie = new Movie();
                            movie.setTitle(item.getString("id_movies"));
                            item.getString("Genres");

//                            movieList.add(movie);
                        }
                    } catch (JSONException e) { e.printStackTrace(); }

                    allMovies.postValue(movieList);
                },
                Throwable::printStackTrace
        );

//        Volley.newRequestQueue().add(stringRequest);

        return allMovies;
    }

    public void refresh() {
        // TODO: Implement refresh function.

    }

    public void insert(Movie movie) {
        MoviesDummy.titles = new String[]{
                "Title 1", "Title 2", "Title 3", "Title4"
        };
        MoviesDummy.genres = new String[][] {
                {
                        "genrer 1a", "genre2a", "genre3a"
                },
                {
                        "genre1b"
                },
                {
                        "genre1c", "genre2c"
                },
                {
                        "genre1d"
                }
        };
        MoviesDummy.writers = new String[][] {
                {
                        "name1a"
                },
                {
                        "name1b", "name2b"
                },
                {
                        "name1c"
                },
                {
                        "name1d"
                }
        };
    }
}

