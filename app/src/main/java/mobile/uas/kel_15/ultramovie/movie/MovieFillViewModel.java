package mobile.uas.kel_15.ultramovie.movie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.repository.GenreRepository;
import mobile.uas.kel_15.ultramovie.repository.MovieRepository;
import mobile.uas.kel_15.ultramovie.repository.WriterRepository;

public class MovieFillViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final WriterRepository writerRepository;

    private MutableLiveData<List<Genre>> _genres;
    private MutableLiveData<List<Writer>> _writers;
    private MutableLiveData<Movie> _movie;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MovieFillViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        genreRepository = new GenreRepository(application);
        writerRepository = new WriterRepository(application);
    }

    public LiveData<List<Genre>> getAllGenres() {
        if(_genres == null) _genres = genreRepository.getAllGenres();
        return _genres;
    }

    public LiveData<List<Writer>> getAllWriters() {
        if(_writers == null) _writers = writerRepository.getAllWriters();
        return _writers;
    }

    // Fungsi-fungsi UI
    public void update(Movie movie) {
        movieRepository.update(movie);
    }

    public void insert(Movie movie) {
        movieRepository.insert(movie);
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
