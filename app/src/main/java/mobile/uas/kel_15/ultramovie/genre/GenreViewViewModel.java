package mobile.uas.kel_15.ultramovie.genre;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.repository.GenreRepository;

public class GenreViewViewModel extends AndroidViewModel {

    private final GenreRepository repository;
    private MutableLiveData<Genre> _genre;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public GenreViewViewModel(Application application) {
        super(application);
        repository = new GenreRepository(application);
    }

    // Fungsi-fungsi UI
    public LiveData<Genre> getGenre(String genreId) {

        if(_genre == null) _genre = repository.getGenre(genreId);
        return _genre;
    }

    public void delete(String genreId) {
        repository.delete(genreId);
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