package mobile.uas.kel_15.ultramovie.writer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.GenresDummy;
import mobile.uas.kel_15.ultramovie.dummy_to_be_removed.WritersDummy;
import mobile.uas.kel_15.ultramovie.genre.GenreAdapter;
import mobile.uas.kel_15.ultramovie.genre.GenreViewModel;
import mobile.uas.kel_15.ultramovie.model.Writer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WriterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WriterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WriterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WriterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WriterFragment newInstance(String param1, String param2) {
        WriterFragment fragment = new WriterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_writer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.writer_recycler_view);
        final WriterAdapter adapter = new WriterAdapter(new WriterAdapter.WriterDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        WriterViewModel viewModel;
        viewModel = new ViewModelProvider(this).get(WriterViewModel.class);

        viewModel.getAllWriters().observe(getViewLifecycleOwner(), adapter::submitList);
    }
}