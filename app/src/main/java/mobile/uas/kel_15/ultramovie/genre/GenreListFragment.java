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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        // Action untuk tambah data baru
        FloatingActionButton fabEdit = view.findViewById(R.id.genre_fab_add);
        fabEdit.setOnClickListener(v -> {
            NavDirections action = GenreListFragmentDirections.actionGenreListFragmentToGenreFillFragment();
            Navigation.findNavController(view).navigate(action);
        });

        // Cek proses loading untuk shimmer layout
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.genre_list_shimmer);

            if (isFinishedLoading != null) {
                if (isFinishedLoading) {
                    recyclerView.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}