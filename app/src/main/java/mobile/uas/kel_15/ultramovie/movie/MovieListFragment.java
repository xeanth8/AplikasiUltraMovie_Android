package mobile.uas.kel_15.ultramovie.movie;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.uas.kel_15.ultramovie.R;

public class MovieListFragment extends Fragment {

    private MovieListViewModel mViewModel;

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.movie_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final MovieListAdapter adapter = new MovieListAdapter();
        recyclerView.setAdapter(adapter);

        mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(MovieListViewModel.class);
        mViewModel.getAllMovies().observe(getViewLifecycleOwner(), adapter::setMovieList);

        mViewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    getActivity().findViewById(R.id.movie_list_progress_bar).setVisibility(View.GONE);
                } else {
                    getActivity().findViewById(R.id.movie_list_progress_bar).setVisibility(View.VISIBLE);
                }
            }
        });




//        getActivity().findViewById(R.id.movie_recycler_view).setOnClickListener(v -> {
//            NavHostFragment.findNavController(this).navigate(R.id.action_movieListFragment_to_movieViewFragment);
//        });
    }

}