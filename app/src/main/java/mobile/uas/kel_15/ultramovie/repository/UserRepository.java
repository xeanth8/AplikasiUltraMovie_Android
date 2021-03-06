package mobile.uas.kel_15.ultramovie.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mobile.uas.kel_15.ultramovie.Commons;
import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.user.LoginViewModel;
import mobile.uas.kel_15.ultramovie.user.RegisterViewModel;

public class UserRepository {
    private Application application;
    String url = Commons.SERVER + "/UserModel.php";
//    private

    public UserRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<User> login(String username, String password) {
        MutableLiveData<User> userData = new MutableLiveData<>();

        LoginViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            try{
                LoginViewModel.processFinished();

                JSONObject jsonObject = new JSONObject(responses);

                // Di getAllWriter, ini jadi Array karena banyak data. Ini jadi Object karena hanya 1.
                JSONObject data = jsonObject.getJSONObject("data");

                User user = new User();

                user.setName(data.getString("nama"));
                user.setCountryOrigin(data.getString("asal_negara"));
                user.setUsername(data.getString("username"));
                user.setPassword(data.getString("password"));

                String level = data.getString("level");
                if (level.equals("admin")) {
                    user.setLevel(User.LEVEL_ADMIN);
                } else {
                    user.setLevel(User.LEVEL_MEMBER);
                }

                String gender = data.getString("jenis_kelamin");
                if (gender.equals("pria")) {
                    user.setGender(User.GENDER_MALE);
                } else {
                    user.setGender(User.GENDER_FEMALE);
                }

                user.setAuthenticated(User.AUTHENTICATED);

                userData.postValue(user);
            } catch (JSONException e){
                LoginViewModel.processFinished();
                userData.postValue(new User(User.AUTHENTICATION_INVALID));
                e.printStackTrace();
            }
        }, error -> {
            LoginViewModel.processFinished();
            userData.postValue(new User(User.UNAUTHENTICATED));
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "find");
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);

        return userData;
    }

    public MutableLiveData<String> register(User user) {
        MutableLiveData<String> registerStatus = new MutableLiveData<>();
        RegisterViewModel.processStarted();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responses -> {
            RegisterViewModel.processFinished();
            System.out.println("responses"+responses);
            if (responses.contains("Duplicate")) registerStatus.postValue("Duplicate");
            else if (responses.contains("Success")) registerStatus.postValue("Success");
        }, error -> {
            RegisterViewModel.processFinished();
            Toast.makeText(application.getApplicationContext(), R.string.app_server_error, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "create");
                params.put("username", user.getUsername());
                params.put("password", user.getPassword());
                params.put("level", user.getLevel());
                params.put("nama", user.getName());
                params.put("jenis_kelamin", user.getGender());
                params.put("asal_negara", user.getCountryOrigin());

                return params;
            }
        };

        Volley.newRequestQueue(application.getApplicationContext()).add(stringRequest);
        return registerStatus;
    }
}
