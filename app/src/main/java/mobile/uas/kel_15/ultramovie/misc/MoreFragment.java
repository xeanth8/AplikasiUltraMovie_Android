package mobile.uas.kel_15.ultramovie.misc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.User;
import mobile.uas.kel_15.ultramovie.movie.MovieViewFragmentDirections;
import mobile.uas.kel_15.ultramovie.user.LoginViewModel;

public class MoreFragment extends Fragment {

    private TextView etName, etUsername, etLevel, etCountry;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.more_text_name);
        etUsername = view.findViewById(R.id.more_text_username);
        etCountry = view.findViewById(R.id.more_text_country);
        etLevel = view.findViewById(R.id.more_text_level);
        etName.setCompoundDrawablePadding(8);

        // Cek apakah ada user. Jika tidak, pop fragment ini dan langsung ke fragment Login
        if (LoginViewModel.getUser().getValue() != null) {

            // Jika ada user, hilangkan fab untuk add jika levelnya LEVEL_MEMBER
            LoginViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
                if (user != null) {
                    etName.setText(user.getName());
                    etUsername.setText(user.getUsername());
                    etCountry.setText(user.getCountryOrigin());

                    if (user.getGender() == User.GENDER_MALE) {
                        etName.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getContext().getResources().getDrawable(R.drawable.ic_baseline_male_24), null);
                    } else if (user.getGender() == User.GENDER_FEMALE) {
                        etName.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, getContext().getResources().getDrawable(R.drawable.ic_baseline_female_24), null);
                    }

                    if (user.getLevel() == User.LEVEL_ADMIN) etLevel.setText(R.string.level_admin);
                    else if (user.getLevel() == User.LEVEL_MEMBER)
                        etLevel.setText(R.string.level_member);
                }
            });
        }






        view.findViewById(R.id.more_card_about_us).setOnClickListener(v -> {
            NavDirections action = MoreFragmentDirections.actionMoreFragmentToAboutUsFragment();
            Navigation.findNavController(view).navigate(action);
        });

        view.findViewById(R.id.more_card_logout).setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(getActivity())
                    .setMessage(R.string.confirmation_logout)
                    .setPositiveButton(R.string.action_logout, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginViewModel.revokeUser();

                            NavController navController = Navigation.findNavController(view);

                            navController.popBackStack();
                            navController.popBackStack();
                            navController.navigate(R.id.loginFragment);
                        }
                    })
                    .setNegativeButton(R.string.dialog_button_cancel, (dialog1, which) -> {})
                    .create().show();
        });

    }

}