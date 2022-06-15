package mobile.uas.kel_15.ultramovie.genre;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.repository.GenreRepository;


public class GenreFillViewModel extends AndroidViewModel {
    private GenreRepository genreRepository;
    private MutableLiveData<Genre> _genre;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public GenreFillViewModel(@NonNull Application application) {
        super(application);
        genreRepository = new GenreRepository(application);
    }

    // Fungsi-fungsi UI

    public void insert(Genre genre) {
        genreRepository.insert(genre);
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
