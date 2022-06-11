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
import mobile.uas.kel_15.ultramovie.genre.GenreListViewModel;
import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.movie.MovieViewViewModel;
import mobile.uas.kel_15.ultramovie.writer.WriterListViewModel;
import mobile.uas.kel_15.ultramovie.writer.WriterViewViewModel;

public class WriterRepository  {
    Application application;
    String url = AppConfig.SERVER + "/WriterModel.php";
    ArrayList<Writer> writerList = new ArrayList<>();

    private final MutableLiveData<List<Writer>> allWriters = new MutableLiveData<>();
    private final MutableLiveData<Writer> writerData = new MutableLiveData<>();

    public WriterRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Writer>> getAllWriters() {
        WriterListViewModel.processStarted();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try {
                WriterListViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                Log.d("TAG", "data length: " + jsonArray.length());

                Writer writer;
                writerList.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    writer = new Writer();

                    writer.setId(String.valueOf(item.getInt("kd_writer")));
                    writer.setName(item.getString("nm_writer"));
                    writer.setEmail(item.getString("email"));
                    writer.setTelepon(item.getString("telepon"));

                    writerList.add(writer);
                }

                allWriters.postValue(writerList);
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
        return allWriters;
    }

    public MutableLiveData<Writer> getWriter(String id) {
        WriterViewViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try{
                WriterViewViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);

                // Di getAllWriter, ini jadi Array karena banyak data. Ini jadi Object karena hanya 1.
                JSONObject data = jsonObject.getJSONObject("data");

                Writer writer;

                writer = new Writer();

                writer.setId(String.valueOf(data.getInt("kd_writer")));
                writer.setName(data.getString("nm_writer"));
                writer.setEmail(data.getString("email"));

                String[] movies =  data.getString("movies").split(",", -1);
                writer.setMovies(movies);

                writerData.postValue(writer);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }, Throwable::printStackTrace) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "find");
                params.put("kd_writer", id);

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);

        return writerData;
    }

    public void insert(Writer writer) {
        // TODO: Implement insert function
    }

    public void delete(String writerId) {
        // TODO: implement delete function
    }
}

