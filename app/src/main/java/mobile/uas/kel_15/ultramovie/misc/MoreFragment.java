package mobile.uas.kel_15.ultramovie.misc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.user.LoginViewModel;

public class MoreFragment extends Fragment {

    private MoreViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MoreViewModel.class);
        TextView tvUsername = getView().findViewById(R.id.more_username);
        tvUsername.setText("Username");


        view.findViewById(R.id.more_card_about_us).setOnClickListener(v -> {
            NavDirections action = MoreFragmentDirections.actionMoreFragmentToAboutUsFragment();
            Navigation.findNavController(getView()).navigate(action);
        });

        view.findViewById(R.id.more_card_logout).setOnClickListener(v -> {
            LoginViewModel.revokeUser();

            NavController navController = Navigation.findNavController(view);

            navController.popBackStack();
            navController.popBackStack();
            navController.navigate(R.id.loginFragment);
        });

    }

}