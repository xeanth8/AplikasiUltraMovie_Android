package mobile.uas.kel_15.ultramovie.writer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.repository.WriterRepository;

public class WriterListViewModel extends AndroidViewModel {
    private final WriterRepository repository;
    private MutableLiveData<List<Writer>> _allWriters;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public WriterListViewModel(Application application) {
        super(application);
        repository = new WriterRepository(application);
    }

    public LiveData<List<Writer>> getAllWriters() {
        if(_allWriters == null) _allWriters = repository.getAllWriters();
        return _allWriters;
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