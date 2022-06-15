package mobile.uas.kel_15.ultramovie.writer;

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
import mobile.uas.kel_15.ultramovie.movie.MovieViewFragmentDirections;

public class WriterListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_writer_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup adapter untuk setiap card writer
        RecyclerView recyclerView = view.findViewById(R.id.writer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final WriterListAdapter adapter = new WriterListAdapter();
        recyclerView.setAdapter(adapter);

        // Inisialisasi ViewModel
        WriterListViewModel mViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(WriterListViewModel.class);

        // Observe perubahan data-data writer
        mViewModel.getAllWriters().observe(getViewLifecycleOwner(), adapter::setWriterList);

        // Action untuk tambah data baru
        FloatingActionButton fabEdit = view.findViewById(R.id.writer_fab_add);
        fabEdit.setOnClickListener(v -> {
            System.out.println("add");
            NavDirections action = WriterListFragmentDirections.actionWriterListFragmentToWriterFillFragment();
            Navigation.findNavController(view).navigate(action);
        });

        // Cek proses loading untuk shimmer layout
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.writer_list_shimmer);

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