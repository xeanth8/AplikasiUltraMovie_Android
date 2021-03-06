package mobile.uas.kel_15.ultramovie.repository;

import android.app.Application;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

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

import mobile.uas.kel_15.ultramovie.Commons;
import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.genre.GenreFillViewModel;
import mobile.uas.kel_15.ultramovie.genre.GenreListViewModel;
import mobile.uas.kel_15.ultramovie.genre.GenreViewViewModel;
import mobile.uas.kel_15.ultramovie.model.Genre;

public class GenreRepository  {
    Application application;
    String url = Commons.SERVER + "/GenreModel.php";
    ArrayList<Genre> genreList = new ArrayList<>();

    private final MutableLiveData<List<Genre>> allGenres = new MutableLiveData<>();
    private final MutableLiveData<Genre> genreData = new MutableLiveData<>();

    public GenreRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Genre>> getAllGenres() {
        GenreListViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try {
                GenreListViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Log.d("TAG", "data length: " + jsonArray.length());

                Genre genre;
                genreList.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    genre = new Genre();

                    genre.setId(String.valueOf(item.getInt("kd_genre")));
                    genre.setName(item.getString("nama_genre"));

                    genreList.add(genre);
                }

                allGenres.postValue(genreList);
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
        return allGenres;
    }

    public MutableLiveData<Genre> getGenre(String id) {

        GenreViewViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try{
                GenreViewViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);

                JSONObject data = jsonObject.getJSONObject("data");

                Genre genre;
                genre = new Genre();

                genre.setId(String.valueOf(data.getInt("kd_genre")));
                genre.setName(data.getString("nama_genre"));

                if (data.getString("movies").equals("null")) genre.setMovies(new String[]{"No movies."});
                else genre.setMovies(data.getString("movies").split(",", -1));

                genreData.postValue(genre);

            }catch (JSONException e){
                e.printStackTrace();}
        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "find");
                params.put("kd_genre", id);

                return params;
            }

        };
        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
        return genreData;
    }

    public void insert(Genre genre) {
        GenreFillViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            GenreFillViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "create");
                params.put("nama_genre", genre.getName());

                return params;
            }
        };
        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }

    public void delete(String genreId) {
        GenreViewViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            GenreViewViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "delete");
                params.put("kd_genre", genreId);

                return params;
            }
        };
        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }
}

