package mobile.uas.kel_15.ultramovie.genre;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.uas.kel_15.ultramovie.R;

public class GenreViewFragment extends Fragment {

    private GenreViewViewModel mViewModel;

    public static GenreViewFragment newInstance() {
        return new GenreViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GenreViewViewModel.class);
        // TODO: Use the ViewModel
    }

}