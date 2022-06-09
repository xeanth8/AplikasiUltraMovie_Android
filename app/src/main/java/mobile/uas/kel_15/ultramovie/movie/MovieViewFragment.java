package mobile.uas.kel_15.ultramovie.movie;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;

public class MovieViewFragment extends Fragment {

    private MovieViewViewModel mViewModel;

    private TextView tvTitle, tvWriter, tvDirector, tvStars, tvSynopsis;
    private ChipGroup cgGenre;

    public static MovieViewFragment newInstance() {
        return new MovieViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_view, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle = getActivity().findViewById(R.id.movie_view_title);
        tvWriter = getActivity().findViewById(R.id.movie_view_writer_content);
        tvDirector = getActivity().findViewById(R.id.movie_view_director_content);
        tvStars = getActivity().findViewById(R.id.movie_view_star_content);
        tvSynopsis = getActivity().findViewById(R.id.movie_view_synopsis_content);
        cgGenre = getActivity().findViewById(R.id.movie_chip_group_genres);

        mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(MovieViewViewModel.class);

        String movieId = MovieViewFragmentArgs.fromBundle(getArguments()).getMovieId();
        mViewModel.getMovie(movieId).observe(getViewLifecycleOwner(), movie -> {
            tvTitle.setText(movie.getTitle());

            String writer = String.join(", ", movie.getWriters());
            tvWriter.setText(writer);

            tvDirector.setText(movie.getDirector());

            for (String genre: movie.getGenres()) {
                Chip chip = new Chip(getContext());
                chip.setText(genre);
                chip.setClickable(false);
                cgGenre.addView(chip);
            }

            String stars = String.join("\n",
                    Arrays.stream(movie.getStars())
                            .map(String::trim)
                            .toArray(String[]::new));
            tvStars.setText(stars);

            tvSynopsis.setText(movie.getSynopsis());


            TextView tvWriterLabel = getActivity().findViewById(R.id.movie_view_writer_label);
            tvWriterLabel.setText("Written by");
            TextView tvDirectorLabel = getActivity().findViewById(R.id.movie_view_director_label);
            tvDirectorLabel.setText("Directed by");
            TextView tvStarsLabel = getActivity().findViewById(R.id.movie_view_star_label);
            tvStarsLabel.setText("Starring");
            TextView tvSynopsisLabel = getActivity().findViewById(R.id.movie_view_synopsis_label);
            tvSynopsisLabel.setText("Synopsis");
        });

        mViewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    getActivity().findViewById(R.id.movie_list_progress_bar).setVisibility(View.GONE);
                } else {
                    getActivity().findViewById(R.id.movie_list_progress_bar).setVisibility(View.VISIBLE);
                }
            }
        });


    }

}