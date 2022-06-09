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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mobile.uas.kel_15.ultramovie.R;

public class GenreListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup adapter untuk setiap card genre
        RecyclerView recyclerView = view.findViewById(R.id.genre_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final GenreListAdapter adapter = new GenreListAdapter();
        recyclerView.setAdapter(adapter);

        // Inisialisasi ViewModel
        GenreListViewModel mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(GenreListViewModel.class);

        // Observe perubahan data-data genre
        mViewModel.getAllGenres().observe(getViewLifecycleOwner(), adapter::setGenreList);

        // Cek proses loading untuk spinner
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    getActivity().findViewById(R.id.genre_list_progress_bar).setVisibility(View.GONE);
                } else {
                    getActivity().findViewById(R.id.genre_list_progress_bar).setVisibility(View.VISIBLE);
                }
            }
        });
    }
}