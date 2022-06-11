package mobile.uas.kel_15.ultramovie.movie;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Genre;
import mobile.uas.kel_15.ultramovie.model.Movie;
import mobile.uas.kel_15.ultramovie.model.Writer;

public class MovieFillFragment extends Fragment {
    private MovieFillViewModel mViewModel;
    private TextInputEditText etTitle, etDirector, etSynopsis;
    private AutoCompleteTextView actvGenre, actvWriter, actvStar;
    private ChipGroup cgGenre, cgWriter, cgStar;
    private Movie movie;
    private ArrayList<String> genres, writers, stars;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_fill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        genres = new ArrayList<>();
        writers = new ArrayList<>();
        stars = new ArrayList<>();

        etTitle = getActivity().findViewById(R.id.movie_fill_field_title);
        etDirector = getActivity().findViewById(R.id.movie_fill_field_director);
        etSynopsis = getActivity().findViewById(R.id.movie_fill_field_synopsis);
        actvGenre = getActivity().findViewById(R.id.movie_fill_field_genre);
        actvWriter = getActivity().findViewById(R.id.movie_fill_field_writer);
        actvStar = getActivity().findViewById(R.id.movie_fill_field_stars);

        cgGenre = getActivity().findViewById(R.id.movie_fill_chip_group_genre);
        cgWriter = getActivity().findViewById(R.id.movie_fill_chip_group_writer);
        cgStar = getActivity().findViewById(R.id.movie_fill_chip_group_stars);

        // Inisialisasi ViewModel
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(MovieFillViewModel.class);
        populateIfEdit();

        mViewModel.getAllGenres().observe(getViewLifecycleOwner(), genresAdapter -> {
            ArrayAdapter<Genre> adapter = new ArrayAdapter<Genre>(getContext(), android.R.layout.simple_dropdown_item_1line, genresAdapter);
            actvGenre.setAdapter(adapter);
            actvGenre.setThreshold(1);

            actvGenre.setOnItemClickListener((parent, view1, position, id) -> {
                actvGenre.setText(null);
                String selected = parent.getItemAtPosition(position).toString();

                if (!genres.contains(selected)) {
                    addChipToGroup(selected, cgGenre, genres);
                    genres.add(selected);
                }
            });
        });

        mViewModel.getAllWriters().observe(getViewLifecycleOwner(), writersAdapter -> {
            ArrayAdapter<Writer> adapter = new ArrayAdapter<Writer>(getContext(), android.R.layout.simple_dropdown_item_1line, writersAdapter);
            actvWriter.setAdapter(adapter);
            actvWriter.setThreshold(1);

            actvWriter.setOnItemClickListener((parent, view1, position, id) -> {
                actvWriter.setText(null);
                String selected = parent.getItemAtPosition(position).toString();

                if (!writers.contains(selected)) {
                    addChipToGroup(selected, cgWriter, writers);
                    writers.add(selected);
                }
            });
        });



    }

    private void populateIfEdit() {
        if (MovieFillFragmentArgs.fromBundle(getArguments()).getMovieId() != null) {
            movie = new Movie();
            movie.setId(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieId());
            movie.setTitle(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieTitle());
            movie.setGenres(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieGenre());
            movie.setWriters(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieWriter());
            movie.setDirector(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieDirector());
            movie.setStars(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieStar());
            movie.setSynopsis(MovieFillFragmentArgs.fromBundle(getArguments()).getMovieSynopsis());
        }

        if (movie.getTitle() != null) etTitle.setText(movie.getTitle());
        if (movie.getDirector() != null) etDirector.setText(movie.getDirector());
        if (movie.getSynopsis() != null) etSynopsis.setText(movie.getSynopsis());

        if (movie.getGenres() != null) {
            for (String genre : movie.getGenres()) {
                addChipToGroup(genre, cgGenre, genres);
                genres.add(genre);
            }
        }

        if (movie.getWriters() != null) {
            for (String writer : movie.getWriters()) {
                addChipToGroup(writer, cgWriter, writers);
                writers.add(writer);
            }
        }

        if (movie.getStars() != null) {
            for (String star: movie.getStars()) {
                addChipToGroup(star, cgStar, stars);
                stars.add(star);
            }
        }
    }

    private void addChipToGroup(String text, ChipGroup chipGroup, ArrayList<String> arrayList) {
        Chip chip = new Chip(getContext());
        chip.setText(text);
        chip.setCheckable(false);
        chip.setCloseIconVisible(true);
        chipGroup.addView(chip);

        chip.setOnCloseIconClickListener(v -> {
            chipGroup.removeView(chip);
            arrayList.remove(text);
        });
    }
}
