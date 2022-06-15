package mobile.uas.kel_15.ultramovie.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;

import mobile.uas.kel_15.ultramovie.R;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;

    private ProgressDialog progressDialog;
    private EditText etName, etUsername, etPassword;
    private Spinner spGender, spCountry, spLevel;
    private Button btRegister;
    private TextInputLayout tilName, tilUsername, tilPassword;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        tilName = view.findViewById(R.id.register_layout_name);
        tilUsername = view.findViewById(R.id.register_layout_username);
        tilPassword = view.findViewById(R.id.register_layout_password);

        etName = view.findViewById(R.id.register_field_name);
        etUsername = view.findViewById(R.id.register_field_username);
        etPassword = view.findViewById(R.id.register_field_password);
        btRegister = view.findViewById(R.id.register_button_register);

        spGender = view.findViewById(R.id.register_spinner_gender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mViewModel.getGenders());
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(genderAdapter);

        spCountry = view.findViewById(R.id.register_spinner_country);
        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mViewModel.getCountries());
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(countryAdapter);

        spLevel = view.findViewById(R.id.register_spinner_level);
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mViewModel.getLevels());
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevel.setAdapter(levelAdapter);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                RegisterViewModel.isFormValid(etName.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString()).observe(getViewLifecycleOwner(), isValid -> {
                    btRegister.setEnabled(isValid);
                });
            }
        });

        tilUsername.setHelperText("Username must consist of 4-16 characters inclusive and may only contain alphanumeric and underscores.");
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                RegisterViewModel.isFormValid(etName.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString()).observe(getViewLifecycleOwner(), isValid -> {
                    btRegister.setEnabled(isValid);
                });
            }
        });

        tilPassword.setHelperText("Password must consist of at least 5 characters.");
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                RegisterViewModel.isFormValid(etName.getText().toString(), etUsername.getText().toString(), etPassword.getText().toString()).observe(getViewLifecycleOwner(), isValid -> {
                    btRegister.setEnabled(isValid);
                });
            }
        });

        view.findViewById(R.id.register_button_register).setOnClickListener(v -> {
            mViewModel.register(
                    etName.getText().toString(),
                    etUsername.getText().toString(),
                    etPassword.getText().toString(),
                    spCountry.getSelectedItem().toString(),
                    spGender.getSelectedItem().toString(),
                    spLevel.getSelectedItem().toString().toLowerCase()
            ).observe(getViewLifecycleOwner(), s -> {
                if (s.equals("Duplicate")) {
                    Toast.makeText(getContext(), "This username already exists.", Toast.LENGTH_SHORT).show();
                    etUsername.requestFocus();
                } else if (s.equals("Success")) {
                    Toast.makeText(getContext(), "Register successful.", Toast.LENGTH_SHORT).show();
                    NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(action);
                }
            });
        });

        // Ke login
        view.findViewById(R.id.register_link_login).setOnClickListener(v -> {
            NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment();
            NavController navController = Navigation.findNavController(view);
//            navController.popBackStack(R.id.loginFragment, true);
            navController.navigate(action);
        });

        progressDialog  = new ProgressDialog(getContext());
        mViewModel.isLoading().observe(getViewLifecycleOwner(), isFinishedLoading -> {
//            ProgressBar progressBar = view.findViewById(R.id.login_progress_bar);
            progressDialog.setMessage(getString(R.string.message_loading));
            progressDialog.setCancelable(false);

            if (isFinishedLoading != null) {
                if (isFinishedLoading) progressDialog.hide();
                else progressDialog.show();
            }
        });
    }

}