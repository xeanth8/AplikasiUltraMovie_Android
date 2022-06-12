package mobile.uas.kel_15.ultramovie.misc;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mobile.uas.kel_15.ultramovie.misc.AboutUsFragment;
import mobile.uas.kel_15.ultramovie.repository.GenreRepository;

public class AboutUsViewModel extends ViewModel {
    private MutableLiveData<List<AboutUsFragment>> _aboutus;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


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