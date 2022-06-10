package mobile.uas.kel_15.ultramovie.movie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.repository.MovieRepository;

public class MovieViewViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private MutableLiveData<Movie> _movie;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MovieViewViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
    }


    // Fungsi-fungsi UI
    public LiveData<Movie> getMovie(String movieId) {
        if(_movie == null) _movie = repository.getMovie(movieId);
        return _movie;
    }

    public void delete(String movieId) {
        repository.delete(movieId);
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