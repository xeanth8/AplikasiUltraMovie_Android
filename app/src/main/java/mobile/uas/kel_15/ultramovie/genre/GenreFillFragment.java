package mobile.uas.kel_15.ultramovie.genre;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Genre;

public class GenreFillFragment extends Fragment {

    private GenreFillViewModel wViewModel;
    private TextInputEditText etName;
    private Genre genre;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_fill, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialToolbar toolbar = view.findViewById(R.id.genre_fill_app_bar);
        toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(view).popBackStack());

        genre = new Genre();

        etName = view.findViewById(R.id.genre_fill_field_name);

        wViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(GenreFillViewModel.class);

        FloatingActionButton fabSave = view.findViewById(R.id.genre_fill_fab_save);
        fabSave.setOnClickListener(v -> {
            //Validasi input field
            if(!(etName.getText().toString().matches("")))
            {
                        genre.setName(etName.getText().toString());

                        wViewModel.insert(genre);

                        view.findViewById(R.id.genre_fill_progress_bar).setVisibility(View.VISIBLE);

                        wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
                            if (isFinishedLoading) {
                                view.findViewById(R.id.genre_fill_progress_bar).setVisibility(View.GONE);
                                NavDirections action = GenreFillFragmentDirections.actionGenreFillFragmentToGenreListFragment();
                                Navigation.findNavController(view).navigate(action);
                            }
                        });
            }
            else
            {
                etName.setError("Genre name is required.");
                etName.requestFocus();
            }

        });
    }
}