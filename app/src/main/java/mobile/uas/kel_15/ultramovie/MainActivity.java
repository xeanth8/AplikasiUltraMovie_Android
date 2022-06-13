package mobile.uas.kel_15.ultramovie;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewKt;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navController = navHostFragment.getNavController();
        BottomNavigationView navigationBarView = findViewById(R.id.bottom_navigation_view);
        NavigationUI.setupWithNavController(navigationBarView, navController);


        // Hide bottom navigation view ketika keyboard muncul
        // Ini menyebabkan status bar jadi putih, masih mencari solusi
        ViewCompat.setOnApplyWindowInsetsListener(navigationBarView.getRootView(), (v, insets) -> {
            boolean imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime());

            // This piece of code makes the bottom navbar always visible when keyboard is not present.
            // Meaning even in loginFragment if the keyboard is not present, the bottom nav will be visible.
            // So this is a workaround for now...
            if (navController.getCurrentDestination().getId() == R.id.loginFragment ||
                navController.getCurrentDestination().getId() == R.id.registerFragment) imeVisible = true;

            ViewKt.setVisible(navigationBarView, !imeVisible);

            return insets;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_container);

        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}