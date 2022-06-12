package mobile.uas.kel_15.ultramovie.writer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.Writer;
import mobile.uas.kel_15.ultramovie.movie.MovieFillViewModel;

public class WriterFillFragment extends Fragment {

    private WriterFillViewModel wViewModel;
    private TextInputEditText etName, etEmail, etPhone;
    private Writer writer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer_fill, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Kode untuk back
        MaterialToolbar toolbar = getView().findViewById(R.id.writer_fill_app_bar);
        toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(getView()).popBackStack();
        });

        writer = new Writer();

        etName = getActivity().findViewById(R.id.writer_fill_field_name);
        etEmail = getActivity().findViewById(R.id.writer_fill_field_email);
        etPhone = getActivity().findViewById(R.id.writer_fill_field_phone);

        wViewModel = new ViewModelProvider((ViewModelStoreOwner) getViewLifecycleOwner()).get(WriterFillViewModel.class);

        FloatingActionButton fabSave = getView().findViewById(R.id.writer_fill_fab_save);
        fabSave.setOnClickListener(v -> {
            Log.d("Tes", "Aman");
            //Validasi input field
            if(!(etName.getText().toString().matches("")))
            {
                if(!(etEmail.getText().toString().matches("")))
                {
                    if(!(etPhone.getText().toString().matches("")))
                    {

                    }
                    else
                    {
                        etPhone.setError("Phone is required.");
                    }
                }
                else
                {
                    etEmail.setError("Email is required.");
                }
            }
            else
            {
                etName.setError("Writer's name is required.");
            }

        });

    }
}