package mobile.uas.kel_15.ultramovie.movie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.repository.MovieRepository;

public class MovieListViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private MutableLiveData<List<Movie>> _allMovies;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MovieListViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        if(_allMovies == null) _allMovies = repository.getAllMovies();
        return _allMovies;
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