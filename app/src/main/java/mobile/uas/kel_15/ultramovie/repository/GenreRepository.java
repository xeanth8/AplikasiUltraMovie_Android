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

import mobile.uas.kel_15.ultramovie.Commons;
import mobile.uas.kel_15.ultramovie.genre.GenreListViewModel;
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
        // TODO: Implement get function
        return null;
    }

    public void insert(Genre genre) {
        // TODO: Implement insert function
    }

    public void delete(String genreId) {
        // TODO: implement delete function
    }
}

