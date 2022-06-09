package mobile.uas.kel_15.ultramovie.genre;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.repository.GenreRepository;

public class GenreViewModel extends AndroidViewModel {
    private GenreRepository repository;
    private LiveData<List<Genre>> allGenres;

    public GenreViewModel(Application application) {
        super(application);
        repository = new GenreRepository(application);
    }

    LiveData<List<Genre>> getAllGenres() {
        if(allGenres == null) {
            allGenres = repository.getAllGenres();
        }
        return allGenres;
    }
}
