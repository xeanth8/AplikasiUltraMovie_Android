package mobile.uas.kel_15.ultramovie.writer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Movie;

public class WriterListViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> _allMovies;

    public final LiveData<List<Movie>> getMovies() {
        return _allMovies;
    }
}