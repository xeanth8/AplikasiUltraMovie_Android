package mobile.uas.kel_15.ultramovie.genre;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;

public class GenreViewFragment extends Fragment {

    TextView tvNameGenre; ///tvMovie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_view, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MaterialToolbar toolbar = getView().findViewById(R.id.genre_view_app_bar);
        toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(getView()).popBackStack();
        });

        Genre genreData = new Genre();
        ///Writer writerData = new Writer();


        tvNameGenre = getActivity().findViewById(R.id.genre_view_name);
        ///tvMovie = getActivity().findViewById(R.id.genre_view_movies_content);

        GenreViewViewModel wViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(GenreViewViewModel.class);

       String genreId = GenreViewFragmentArgs.fromBundle(getArguments()).getGenreId();

       wViewModel.getGenre(genreId).observe(getViewLifecycleOwner(), genre -> {
       genreData.setId(genre.getId());
       genreData.setName(genre.getName());
       /// writerData.setMovies(writerData.getMovies());


       tvNameGenre.setText(genre.getName());

       ///    String movies = String.join("\n",
       ///            Arrays.stream(writerData.getMovies())
       ///                    .map(String::trim)
       ///                    .toArray(String[]::new));

       ///tvMovie.setText(movies);


      });

        wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading ->{
            ShimmerFrameLayout shimmerFrameLayout = getActivity().findViewById(R.id.genre_view_shimmer);
            NestedScrollView nestedScrollView = getActivity().findViewById(R.id.genre_view_main);

            if(isFinishedLoading != null){
                if(isFinishedLoading){
                    nestedScrollView.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.setVisibility(View.GONE);
                }else{
                    nestedScrollView.setVisibility(View.GONE);
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        ActionMenuItemView amDelete = getActivity().findViewById(R.id.app_bar_item_delete);
        amDelete.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(getActivity())
                    .setTitle(R.string.genre_view_delete_title)
                    .setMessage(R.string.genre_view_delete_message)
                    .setPositiveButton(R.string.dialog_button_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           wViewModel.delete(genreId);

                            getView().findViewById(R.id.genre_view_progress_bar).setVisibility(View.VISIBLE);
                            wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
                                if (isFinishedLoading) {
                                    getView().findViewById(R.id.genre_view_progress_bar).setVisibility(View.GONE);
                                    NavDirections action = GenreViewFragmentDirections.actionGenreViewFragmentToGenreListFragment();
                                    Navigation.findNavController(getView()).navigate(action);
                                }
                            });
                        }
                    })
                    .setNegativeButton(R.string.dialog_button_cancel, (dialog1, which) -> {})
                    .create().show();
        });
    }

}