package mobile.uas.kel_15.ultramovie.misc;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import org.w3c.dom.Text;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.writer.WriterListAdapter;

public class AboutUsFragment extends Fragment {

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
        String mailTitle = "Butuh Bantuan | Aplikasi Ultra Movie";
        super.onViewCreated(view, savedInstanceState);
        MaterialCardView contactfendy = getView().findViewById(R.id.contact_fendy);
        contactfendy.setOnClickListener(v -> {
            String[] mail = {"s31190038@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView contactjohanes = getView().findViewById(R.id.contact_johanes);
        contactjohanes.setOnClickListener(v -> {
            String[] mail = {"s31190042@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView contactkosasi = getView().findViewById(R.id.contact_kosasi);
        contactkosasi.setOnClickListener(v -> {
            String[] mail = {"s31190050@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView contactmichelle = getView().findViewById(R.id.contact_michelle);
        contactmichelle.setOnClickListener(v -> {
            String[] mail = {"s31190052@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView phones = getView().findViewById(R.id.contact_phone);
        phones.setOnClickListener(v -> {
            String no = "081283456789";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.fromParts("tel", no, null));
            startActivity(intent);
        });

        MaterialCardView links = getView().findViewById(R.id.source);
        links.setOnClickListener(v -> {
            String url = "";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
}
