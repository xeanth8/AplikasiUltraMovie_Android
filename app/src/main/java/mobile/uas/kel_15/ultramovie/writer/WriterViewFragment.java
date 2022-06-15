package mobile.uas.kel_15.ultramovie.writer;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Writer;


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

        // Kode untuk back
        MaterialToolbar toolbar = view.findViewById(R.id.writer_view_app_bar);
        toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });

        Writer writerData = new Writer();
        tvName = view.findViewById(R.id.writer_view_name);
        tvEmail = view.findViewById(R.id.writer_view_email_content);
        tvPhone = view.findViewById(R.id.writer_view_phone_content);
        tvMovies = view.findViewById(R.id.writer_view_movies_content);

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
            ShimmerFrameLayout shimmerFrameLayout = view.findViewById(R.id.writer_view_shimmer);
            NestedScrollView nestedScrollView = view.findViewById(R.id.writer_view_main);

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

        ActionMenuItemView amDelete = view.findViewById(R.id.app_bar_item_delete);
        amDelete.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(getContext())
                    .setTitle(R.string.writer_view_delete_title)
                    .setMessage(R.string.writer_view_delete_message)
                    .setPositiveButton(R.string.dialog_button_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wViewModel.delete(writerId);

                            view.findViewById(R.id.writer_view_progress_bar).setVisibility(View.VISIBLE);
                            wViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
                                if (isFinishedLoading) {
                                    view.findViewById(R.id.writer_view_progress_bar).setVisibility(View.GONE);
                                    NavDirections action = WriterViewFragmentDirections.actionWriterViewFragmentToWriterListFragment();
                                    Navigation.findNavController(view).navigate(action);
                                }
                            });
                        }
                    })
                    .setNegativeButton(R.string.dialog_button_cancel, (dialog1, which) -> {})
                    .create().show();
        });

    }
}