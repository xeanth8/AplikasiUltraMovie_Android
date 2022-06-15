package mobile.uas.kel_15.ultramovie.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import mobile.uas.kel_15.ultramovie.Commons;
import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.repository.UserRepository;

public class RegisterViewModel extends AndroidViewModel {
    private final UserRepository repository;

    private final String[] GENDER = {getApplication().getString(R.string.gender_male), getApplication().getString(R.string.gender_female)};
    private final String[] LEVEL = {getApplication().getString(R.string.level_member), getApplication().getString(R.string.level_admin)};
    private ArrayList<String> COUNTRIES = new ArrayList<>();

//    private User userData = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private static MutableLiveData<Boolean> isFormValid = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<String> register(String name, String username, String password, String country, String gender, String level) {
        User user = new User();

        user.setName(name);
        user.setUsername(username);
        user.setPassword(Commons.md5(password));
        user.setCountryOrigin(country);

        if (gender.equals(getApplication().getString(R.string.gender_male))) user.setGender(User.GENDER_MALE);
        else user.setGender(User.GENDER_FEMALE);

        user.setLevel(level);

        return repository.register(user);
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
        return !name.equals("");
    }

    private static boolean checkUsername(String username) {
        return username.matches("[a-zA-Z0-9_]+") && username.length() >= 4 && username.length() <= 16;
    }

    private static boolean checkPassword(String password) {
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
            COUNTRIES.add(locale.getDisplayCountry(Locale.forLanguageTag("en-US")));
        }

        Set<String> s = new LinkedHashSet<>(COUNTRIES);
        COUNTRIES = new ArrayList<>(s);
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