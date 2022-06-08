package mobile.uas.kel_15.ultramovie.movie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.MoviesDummy;
import mobile.uas.kel_15.ultramovie.model.Movie;

public class MovieListViewModel extends AndroidViewModel {
    private MovieRepository repository;
    private MutableLiveData<List<Movie>> allMovies;

    public MovieListViewModel(Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    MutableLiveData<List<Movie>> getAllMovies() {
        if(allMovies == null) {
            allMovies = repository.getAllMovies();
        }
        return allMovies;
    }

    public void insert(Movie movie) {
        repository.insert(movie);
    }

    public void refresh() {
        repository.refresh();
    }
}
