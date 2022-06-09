package mobile.uas.kel_15.ultramovie.genre;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.uas.kel_15.ultramovie.R;

public class GenreListFragment extends Fragment {

    public GenreListFragment() {
        // Required empty public constructor
    }

    public static GenreListFragment newInstance() {
        return new GenreListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.genre_recycler_view);
        final GenreAdapter adapter = new GenreAdapter(new GenreAdapter.GenreDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        GenreViewModel viewModel;
        viewModel = new ViewModelProvider(this).get(GenreViewModel.class);

        viewModel.getAllGenres().observe(getViewLifecycleOwner(), adapter::submitList);
    }
}