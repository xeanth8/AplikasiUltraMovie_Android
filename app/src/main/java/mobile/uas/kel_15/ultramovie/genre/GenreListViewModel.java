package mobile.uas.kel_15.ultramovie.genre;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.repository.GenreRepository;
import mobile.uas.kel_15.ultramovie.repository.MovieRepository;

public class GenreListViewModel extends AndroidViewModel {
    private GenreRepository repository;
    private MutableLiveData<List<Genre>> _allGenres;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public GenreListViewModel(Application application) {
        super(application);
        repository = new GenreRepository(application);
    }

    public LiveData<List<Genre>> getAllGenres() {
        if(_allGenres == null) _allGenres = repository.getAllGenres();
        return _allGenres;
    }


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