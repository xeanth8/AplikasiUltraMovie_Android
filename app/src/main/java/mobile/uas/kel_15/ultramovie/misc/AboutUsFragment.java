package mobile.uas.kel_15.ultramovie.misc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import mobile.uas.kel_15.ultramovie.R;

public class AboutUsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String mailTitle = "Butuh Bantuan | Aplikasi Ultra Movie";

        MaterialToolbar toolbar = view.findViewById(R.id.about_us_app_bar);
        toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(view).popBackStack());


        MaterialCardView contactfendy = view.findViewById(R.id.contact_fendy);
        contactfendy.setOnClickListener(v -> {
            String[] mail = {"s31190038@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView contactjohanes = view.findViewById(R.id.contact_johanes);
        contactjohanes.setOnClickListener(v -> {
            String[] mail = {"s31190042@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView contactkosasi = view.findViewById(R.id.contact_kosasi);
        contactkosasi.setOnClickListener(v -> {
            String[] mail = {"s31190050@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView contactmichelle = view.findViewById(R.id.contact_michelle);
        contactmichelle.setOnClickListener(v -> {
            String[] mail = {"s31190052@student.ubm.ac.id"};
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, mail);
            intent.putExtra(Intent.EXTRA_SUBJECT, mailTitle);
            startActivity(intent);
        });

        MaterialCardView phones = view.findViewById(R.id.contact_phone);
        phones.setOnClickListener(v -> {
            String no = "081283456789";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.fromParts("tel", no, null));
            startActivity(intent);
        });

        MaterialCardView links = view.findViewById(R.id.source);
        links.setOnClickListener(v -> {
            String url = "https://github.com/xeanth8/AplikasiUltraMovie_Android/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
}
