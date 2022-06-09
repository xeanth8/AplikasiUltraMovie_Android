package mobile.uas.kel_15.ultramovie.writer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.uas.kel_15.ultramovie.R;

public class WriterListFragment extends Fragment {

    private WriterListViewModel mViewModel;

    public static WriterListFragment newInstance() {
        return new WriterListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_writer_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WriterListViewModel.class);
        // TODO: Use the ViewModel
    }

}