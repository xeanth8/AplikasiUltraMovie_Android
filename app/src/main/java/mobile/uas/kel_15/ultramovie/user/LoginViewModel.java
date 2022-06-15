package mobile.uas.kel_15.ultramovie.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import mobile.uas.kel_15.ultramovie.Commons;
import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.repository.UserRepository;

public class LoginViewModel extends AndroidViewModel {
    private final UserRepository repository;

    private static MutableLiveData<User> userData = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private static MutableLiveData<Boolean> isFormValid = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<User> login(String username, String password) {
        String hashedPassword = Commons.md5(password);

        userData = repository.login(username, hashedPassword);

        return userData;
    }

    public static LiveData<User> getUser() {
        return userData;
    }

    public static void revokeUser() {
        userData.setValue(null);
    }

    public static LiveData<Boolean> isFormValid(String username, String password) {
        isFormValid = new MutableLiveData<>();
        isFormValid.postValue(!username.equals("") && !password.equals(""));
        return isFormValid;
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