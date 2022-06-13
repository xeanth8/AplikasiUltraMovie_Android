package mobile.uas.kel_15.ultramovie.user;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mobile.uas.kel_15.ultramovie.R;
import mobile.uas.kel_15.ultramovie.model.User;

public class LoginFragment extends Fragment {
    private LoginViewModel mViewModel;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        etUsername = view.findViewById(R.id.login_field_username);
        etPassword = view.findViewById(R.id.login_field_password);

        view.findViewById(R.id.login_button_login).setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            // Sembunyikan keyboard ketika tombol Login ditekan.
            try {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }

            mViewModel.login(username, password).observe(getViewLifecycleOwner(), user -> {
                if(user.getAuthenticated() == User.AUTHENTICATION_INVALID) {
                    Toast.makeText(getContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                } else if (user.getAuthenticated() == User.AUTHENTICATED) {
                    BottomNavigationView navigationBarView = getActivity().findViewById(R.id.bottom_navigation_view);
                    if (user.getLevel() == User.LEVEL_MEMBER) {
                        navigationBarView.getMenu().findItem(R.id.writerListFragment).setVisible(false);
                        navigationBarView.getMenu().findItem(R.id.genreListFragment).setVisible(false);
                    } else if (user.getLevel() == User.LEVEL_ADMIN){
                        navigationBarView.getMenu().findItem(R.id.writerListFragment).setVisible(true);
                        navigationBarView.getMenu().findItem(R.id.genreListFragment).setVisible(true);
                    }

                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                    navController.navigate(R.id.movieListFragment);
                }
            });
        });


        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
            ProgressBar progressBar = view.findViewById(R.id.login_progress_bar);

            if (isFinishedLoading != null) {
                if (isFinishedLoading) progressBar.setVisibility(View.INVISIBLE);
                else progressBar.setVisibility(View.VISIBLE);
            }
        });


        // Ke register
        view.findViewById(R.id.textView).setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();
            Navigation.findNavController(view).navigate(action);
//            NavController navController = Navigation.findNavController(view);
//            navController.navigate(R.id.registerFragment);
        });
    }
}