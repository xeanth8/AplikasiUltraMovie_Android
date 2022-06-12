package mobile.uas.kel_15.ultramovie.misc;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.writer.WriterListAdapter;

public class AboutUsFragment extends Fragment {

    private AboutUsViewModel mViewModel;

    public static AboutUsFragment newInstance() {
        return new AboutUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AboutUsViewModel.class);
        MaterialToolbar toolbar = getView().findViewById(R.id.contact_list);
        toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(getView()).popBackStack();
        });

        findViewById(R.id.contact_fendy).setOnClickListener{
            String[] mail = {"s31190038@student.ubm.ac.id"};
        };

        findViewById(R.id.contact_johanes).setOnClickListener{
            String[] mail = {"s31190042@student.ubm.ac.id"};
        };

        findViewById(R.id.contact_kosasi).setOnClickListener{
            String[] mail = {"s31190050@student.ubm.ac.id"};
        };

        findViewById(R.id.contact_michelle).setOnClickListener{
            String[] mail = {"s31190052@student.ubm.ac.id"};
        };

        findViewById(R.id.contact_phone).setOnClickListener{
            String no = "081234567890";
        };

        findViewById(R.id.link).setOnClickListener{
            String url = "";
        };

    }
}
