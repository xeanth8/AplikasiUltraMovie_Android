package mobile.uas.kel_15.ultramovie.movie;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Movie;

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

        Movie movieData = new Movie();
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
            movieData.setId(movie.getId());
            movieData.setTitle(movie.getTitle());
            movieData.setGenres(movie.getGenres());
            movieData.setWriters(movie.getWriters());
            movieData.setDirector(movie.getDirector());
            movieData.setStars(movie.getStars());
            movieData.setSynopsis(movie.getSynopsis());

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

        FloatingActionButton fabEdit = getView().findViewById(R.id.movie_view_fab_edit);
        fabEdit.setOnClickListener(v -> {
            System.out.println("edit");
            NavDirections action = MovieViewFragmentDirections.actionMovieViewFragmentToMovieFillFragment(
                    movieData.getId(),
                    movieData.getTitle(),
                    movieData.getGenres(),
                    movieData.getWriters(),
                    movieData.getStars(),
                    movieData.getSynopsis(),
                    movieData.getDirector()
            );
            Navigation.findNavController(getView()).navigate(action);
        });

        // Cek proses loading untuk shimmer layout
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = getActivity().findViewById(R.id.movie_view_shimmer);
            NestedScrollView nestedScrollView = getActivity().findViewById(R.id.movie_view_main);

            if (isFinishedLoading != null) {
                if (isFinishedLoading) {
                    nestedScrollView.setVisibility(View.VISIBLE);
                    fabEdit.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.setVisibility(View.GONE);
                } else {
                   nestedScrollView.setVisibility(View.GONE);
                   fabEdit.setVisibility(View.GONE);
                   shimmerFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        ActionMenuItemView amDelete = getView().findViewById(R.id.app_bar_item_delete);
        amDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.movie_view_message_delete)
                            .setPositiveButton(R.string.dialog_button_yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mViewModel.delete(movieId);

                                    getView().findViewById(R.id.movie_view_progress_bar).setVisibility(View.VISIBLE);
                                    mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
                                        if (isFinishedLoading) {
                                            getView().findViewById(R.id.movie_view_progress_bar).setVisibility(View.GONE);
                                            NavDirections action = MovieViewFragmentDirections.actionMovieViewFragmentToMovieListFragment();
                                            Navigation.findNavController(getView()).navigate(action);
                                        }
                                    });
                                }
                            })
                            .setNegativeButton(R.string.dialog_button_no, (dialog1, which) -> {})
                    .create().show();
        });
    }

}