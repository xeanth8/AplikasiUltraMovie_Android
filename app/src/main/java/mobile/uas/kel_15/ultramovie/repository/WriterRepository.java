package mobile.uas.kel_15.ultramovie.repository;

import android.app.Application;
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
import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.writer.WriterFillViewModel;
import mobile.uas.kel_15.ultramovie.writer.WriterListViewModel;
import mobile.uas.kel_15.ultramovie.writer.WriterViewViewModel;

public class WriterRepository  {
    Application application;
    String url = Commons.SERVER + "/WriterModel.php";
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

                // Log.d("Data:", String.valueOf(data));

                writer = new Writer();

                writer.setId(String.valueOf(data.getInt("kd_writer")));
                writer.setName(data.getString("nm_writer"));
                writer.setEmail(data.getString("email"));
                writer.setTelepon(data.getString("telepon"));

                String[] movies =  data.getString("movies").split(",", -1);
                writer.setMovies(movies);

                writerData.postValue(writer);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
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
        WriterFillViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            WriterFillViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "create");
                params.put("nm_writer", writer.getName());
                params.put("email", writer.getEmail());
                params.put("telepon", writer.getTelepon());

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }

    public void delete(String writerId) {
        WriterFillViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            WriterFillViewModel.processFinished();

        }, error -> {
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "delete");
                params.put("kd_writer", writerId);

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
    }
}

