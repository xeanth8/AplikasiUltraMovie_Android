package mobile.uas.kel_15.ultramovie.writer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.movie.MovieViewFragmentDirections;

public class WriterFillFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer_fill, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabSave = getView().findViewById(R.id.writer_fill_fab_save);
        fabSave.setOnClickListener(v -> {
            System.out.println("save");
//            NavDirections action = MovieViewFragmentDirections.actionMovieViewFragmentToMovieFillFragment(
//                    movieData.getId(),
//                    movieData.getTitle(),
//                    movieData.getGenres(),
//                    movieData.getWriters(),
//                    movieData.getStars(),
//                    movieData.getSynopsis(),
//                    movieData.getDirector()
//            );
//            Navigation.findNavController(getView()).navigate(action);
        });

    }
}