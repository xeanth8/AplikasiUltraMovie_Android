package mobile.uas.kel_15.ultramovie.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Objects;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.user.LoginViewModel;

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

         // Cek apakah ada user. Jika tidak, pop fragment ini dan langsung ke fragment Login
        if (LoginViewModel.getUser().getValue() != null) {

        // Jika ada user, hilangkan fab untuk add jika levelnya LEVEL_MEMBER
            LoginViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
                if (Objects.equals(user.getLevel(), User.LEVEL_MEMBER)) {
                    view.findViewById(R.id.movie_fab_add).setVisibility(View.GONE);
                }
            });
        } else {
            NavController navController = Navigation.findNavController(view);
            navController.popBackStack();
            navController.navigate(R.id.loginFragment);
        }


        // Setup adapter untuk setiap card movie
        RecyclerView recyclerView = view.findViewById(R.id.movie_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final MovieListAdapter adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

        // Inisialisasi ViewModel
        mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(MovieListViewModel.class);

        // Observe perubahan data-data movie dan berikan data movienya ke Adapter
        if(LoginViewModel.getUser().getValue() != null) mViewModel.getAllMovies().observe(getViewLifecycleOwner(), adapter::setMovieList);

        view.findViewById(R.id.movie_fab_add).setOnClickListener(v -> {
            NavDirections action = MovieListFragmentDirections.actionMovieListFragmentToMovieFillFragment(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                    );
            Navigation.findNavController(view).navigate(action);
        });

        // Cek proses loading untuk shimmer layout
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.movie_list_shimmer);

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