package mobile.uas.kel_15.ultramovie.user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.repository.UserRepository;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository repository;

    private User user;
    private static MutableLiveData<User> userData = new MutableLiveData<>();
    private static MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public LiveData<User> login(String username, String password) {
        String hashedPassword = md5(password);

        userData = repository.login(username, hashedPassword);

        return userData;
    }

    public static LiveData<User> getUser() {
        return userData;
    }

    public static void revokeUser() {
        userData.postValue(null);
    }

    private String md5(String plainPassword) {
        StringBuilder hash = new StringBuilder();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(plainPassword.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            BigInteger no = new BigInteger(1, digest);
            hash = new StringBuilder(no.toString(16));

            while(hash.length() < 32) {
                hash.insert(0, "0");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return String.valueOf(hash);
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