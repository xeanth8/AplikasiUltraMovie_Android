package mobile.uas.kel_15.ultramovie.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Locale;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.repository.UserRepository;

public class RegisterViewModel extends AndroidViewModel {
    private UserRepository repository;

    private final String[] GENDER = {getApplication().getString(R.string.gender_male), getApplication().getString(R.string.gender_female)};
    private final String[] LEVEL = {getApplication().getString(R.string.level_member), getApplication().getString(R.string.level_admin)};
    private final ArrayList<String> COUNTRIES = new ArrayList<>();

    private User user;
//    private User userData = new MutableLiveData<>();
    private static MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private static MutableLiveData<Boolean> isFormValid = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void register(User user) {
        repository.register(user);
    }

    public static LiveData<Boolean> isFormValid(String name, String username, String password) {
        isFormValid = new MutableLiveData<>();
        isFormValid.postValue(false);
        boolean passwordValid = checkPassword(password);
        boolean usernameValid = checkUsername(username);
        boolean nameValid = checkName(name);

        isFormValid.postValue(passwordValid && usernameValid && nameValid);
        return isFormValid;
    }

    private static boolean checkName(String name) {
        System.out.println("name" + !name.equals(""));
        return !name.equals("");
    }

    private static boolean checkUsername(String username) {
        System.out.println("userregex" + username.matches(".matches(\"[A-Za-z0-9_]+\")"));
        System.out.println("userleng" + username.length());
        return username.matches("[a-zA-Z0-9_]+") && username.length() >= 4 && username.length() <= 16;
    }

    private static boolean checkPassword(String password) {
        System.out.println("pwempt" + !password.equals(""));
        System.out.println("pwleng" + password.length());
        return !password.equals("") && password.length() >= 5;
    }


    public String[] getGenders() {
        return GENDER;
    }

    public String[] getLevels() {
        return LEVEL;
    }

    public ArrayList<String> getCountries() {
        for (String country : Locale.getISOCountries()) {
            Locale locale = new Locale("en", country);
            COUNTRIES.add(locale.getDisplayCountry());
        }

        return COUNTRIES;
    }

    // Tidak perlu diubah
    public static void processStarted() {
        isLoading.postValue(false);
    }

    public static void processFinished() {
        isLoading.postValue(true);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}