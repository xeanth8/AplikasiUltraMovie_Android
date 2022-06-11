package mobile.uas.kel_15.ultramovie.writer;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.repository.WriterRepository;

public class WriterFillViewModel extends AndroidViewModel {

    private WriterRepository writerRepository;
    private MutableLiveData<Writer> _writer;
    private static final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public WriterFillViewModel(@NonNull Application application) {
        super(application);
        writerRepository = new WriterRepository(application);
    }

    // Fungsi-fungsi UI

    public void insert(Writer writer) {
        writerRepository.insert(writer);
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
