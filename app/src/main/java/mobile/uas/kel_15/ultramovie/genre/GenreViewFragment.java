package mobile.uas.kel_15.ultramovie.genre;

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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Genre;

public class GenreViewFragment extends Fragment {

    TextView tvNameGenre, tvMovie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_view, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MaterialToolbar toolbar = view.findViewById(R.id.genre_view_app_bar);
        toolbar.setNavigationOnClickListener(v -> {
        Navigation.findNavController(view).popBackStack();
        });

        tvNameGenre = view.findViewById(R.id.genre_view_name);
        tvMovie = view.findViewById(R.id.genre_view_movies_content);

        GenreViewViewModel wViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(GenreViewViewModel.class);

        String genreId = GenreViewFragmentArgs.fromBundle(getArguments()).getGenreId();

        wViewModel.getGenre(genreId).observe(getViewLifecycleOwner(), genre -> {
            genre.setId(genre.getId());
            genre.setName(genre.getName());

            tvNameGenre.setText(genre.getName());

            if (genre.getMovies() != null) {
                String movies = String.join("\n", Arrays.stream(genre.getMovies()).map(String::trim).toArray(String[]::new));
                tvMovie.setText(movies);
            }
      });

        wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading ->{
            ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.genre_view_shimmer);
            NestedScrollView nestedScrollView = view.findViewById(R.id.genre_view_main);

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

        ActionMenuItemView amDelete = view.findViewById(R.id.app_bar_item_delete);
        amDelete.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle(R.string.genre_view_delete_title)
                    .setMessage(R.string.genre_view_delete_message)
                    .setPositiveButton(R.string.dialog_button_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           wViewModel.delete(genreId);

                            view.findViewById(R.id.genre_view_progress_bar).setVisibility(View.VISIBLE);
                            wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
                                if (isFinishedLoading) {
                                    view.findViewById(R.id.genre_view_progress_bar).setVisibility(View.GONE);
                                    NavDirections action = GenreViewFragmentDirections.actionGenreViewFragmentToGenreListFragment();
                                    Navigation.findNavController(view).navigate(action);
                                }
                            });
                        }
                    })
                    .setNegativeButton(R.string.dialog_button_cancel, (dialog1, which) -> {})
                    .create().show();
        });
    }

}