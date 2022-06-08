package mobile.uas.kel_15.ultramovie.genre;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.GenresDummy;
import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.MoviesDummy;
import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;

public class GenreRepository {
    private MutableLiveData<List<Genre>> allGenres = new MutableLiveData<>();

    public GenreRepository(Application application) {
        List<Genre> genreList;
        genreList = new ArrayList<>();
        for(int i = 0; i < GenresDummy.names.length; i++) {
            genreList.add(new Genre(
                    GenresDummy.names[i]
            ));
        }

        allGenres.postValue(genreList);
    }

    public LiveData<List<Genre>> getAllGenres() {
        return allGenres;
    }
}

