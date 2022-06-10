package mobile.uas.kel_15.ultramovie.movie;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mobile.uas.kel_15.ultramovie.R;

public class MovieListFragment extends Fragment {

    private MovieListViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup adapter untuk setiap card movie
        RecyclerView recyclerView = view.findViewById(R.id.movie_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final MovieListAdapter adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

        // Inisialisasi ViewModel
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(MovieListViewModel.class);

        // Observe perubahan data-data movie dan berikan data movienya ke Adapter
        mViewModel.getAllMovies().observe(getViewLifecycleOwner(), adapter::setMovieList);

//        FloatingActionButton fab;
//        fab = getActivity().findViewById(R.id.movie_fab_add).setOnClickListener(v -> {
//            NavDirections action = MovieListFragmentDirections.actionMovieListFragmentToMovieFillFragment(null);
////            Navigation.findNavController().navigate(action);
//        });

        // Cek proses loading untuk shimmer layout
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = getActivity().findViewById(R.id.movie_list_shimmer);

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