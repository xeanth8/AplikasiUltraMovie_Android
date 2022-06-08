package mobile.uas.kel_15.ultramovie.writer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;

public class WriterViewModel extends AndroidViewModel {
    private WriterRepository repository;
    private MutableLiveData<List<Writer>> allWriters;

    public WriterViewModel(Application application) {
        super(application);
        repository = new WriterRepository(application);
    }

    MutableLiveData<List<Writer>> getAllWriters() {
        if(allWriters == null) {
            allWriters = repository.getAllWriters();
        }
        return allWriters;
    }
}
