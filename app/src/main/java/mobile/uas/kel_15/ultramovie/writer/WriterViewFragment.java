package mobile.uas.kel_15.ultramovie.writer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.chip.Chip;

import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.writer.WriterViewFragmentArgs;
import mobile.uas.kel_15.ultramovie.writer.WriterViewViewModel;

public class WriterViewFragment extends Fragment {

    TextView tvName, tvEmail, tvPhone, tvMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer_view, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Writer writerData = new Writer();
        tvName = getActivity().findViewById(R.id.writer_view_name);
        tvEmail = getActivity().findViewById(R.id.writer_view_email_content);
        tvPhone = getActivity().findViewById(R.id.writer_view_phone_content);
        tvMovies = getActivity().findViewById(R.id.writer_view_movies_content);

        // Inisialisasi ViewModel
        WriterViewViewModel wViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(WriterViewViewModel.class);

        // Ambil safeargs dari Fragment WriterList
        String writerId = WriterViewFragmentArgs.fromBundle(getArguments()).getWriterId();

//      Log.d("Test", writerId);

        // Observe perubahan data movie
        wViewModel.getWriter(writerId).observe(getViewLifecycleOwner(), writer -> {
            writerData.setId(writer.getId());
            writerData.setName(writer.getName());
            writerData.setEmail(writer.getEmail());
            writerData.setTelepon(writer.getTelepon());
            writerData.setMovies(writer.getMovies());

            tvName.setText(writer.getName());

            tvEmail.setText(writer.getEmail());

            tvPhone.setText(writer.getTelepon());

            String movies = String.join("\n",
                    Arrays.stream(writer.getMovies())
                            .map(String::trim)
                            .toArray(String[]::new));
            tvMovies.setText(movies);

        });

        // Cek proses loading untuk shimmer layout
        wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ShimmerFrameLayout shimmerFrameLayout = getActivity().findViewById(R.id.writer_view_shimmer);
            NestedScrollView nestedScrollView = getActivity().findViewById(R.id.writer_view_main);

            if (isFinishedLoading != null) {
                if (isFinishedLoading) {
                    nestedScrollView.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.setVisibility(View.GONE);
                } else {
                    nestedScrollView.setVisibility(View.GONE);
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        ActionMenuItemView amDelete = getActivity().findViewById(R.id.app_bar_item_delete);
        amDelete.setOnClickListener(v -> {
            wViewModel.delete(writerId);
        });

    }
}