package mobile.uas.kel_15.ultramovie.writer;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.repository.WriterRepository;

public class WriterViewViewModel extends AndroidViewModel {

    private final WriterRepository repository;
    private MutableLiveData<Writer> _writer;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public WriterViewViewModel(Application application) {
        super(application);
        repository = new WriterRepository(application);
    }

    // Fungsi-fungsi UI
    public LiveData<Writer> getWriter(String writerId) {

        if(_writer == null) _writer = repository.getWriter(writerId);
        return _writer;
    }

    public void delete(String writerId) {
        repository.delete(writerId);
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
