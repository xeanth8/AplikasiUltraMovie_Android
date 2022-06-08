package mobile.uas.kel_15.ultramovie.writer;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.MoviesDummy;
import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.WritersDummy;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;

public class WriterRepository {
    private MutableLiveData<List<Writer>> allWriters = new MutableLiveData<>();

    public WriterRepository(Application application) {
        List<Writer> writerList;
        writerList = new ArrayList<>();
        for(int i = 0; i < WritersDummy.names.length; i++) {
            writerList.add(new Writer(
                    WritersDummy.names[i]
            ));
        }

        allWriters.postValue(writerList);
    }

    public MutableLiveData<List<Writer>> getAllWriters() {
        return allWriters;
    }
}

