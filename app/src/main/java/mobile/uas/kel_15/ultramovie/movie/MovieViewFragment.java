package mobile.uas.kel_15.ultramovie.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;

public class MovieViewFragment extends Fragment {

    private TextView tvTitle, tvWriter, tvDirector, tvStars, tvSynopsis;
    private ChipGroup cgGenre;

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

        // Inisialisasi ViewModel
        MovieViewViewModel mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(MovieViewViewModel.class);

        // Ambil safeargs dari Fragment MovieList
        String movieId = MovieViewFragmentArgs.fromBundle(getArguments()).getMovieId();

        // Observe perubahan data movie
        mViewModel.getMovie(movieId).observe(getViewLifecycleOwner(), movie -> {
            tvTitle.setText(movie.getTitle());

            String writer = String.join(", ", movie.getWriters());
            tvWriter.setText(writer);

            tvDirector.setText(movie.getDirector());

            // Buat chip untuk setiap genre
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
        });

        // Cek proses loading untuk shimmer layout
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = getActivity().findViewById(R.id.movie_view_shimmer);
            NestedScrollView nestedScrollView = getActivity().findViewById(R.id.movie_view_main);

            if (isFinishedLoading != null) {
                if (isFinishedLoading) {
                    nestedScrollView.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.setVisibility(View.GONE);
                } else {
                   nestedScrollView.setVisibility(View.GONE);
                   shimmerFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        ActionMenuItemView amDelete = getActivity().findViewById(R.id.app_bar_item_delete);
        amDelete.setOnClickListener(v -> {
            mViewModel.delete(movieId);
        });


    }

}