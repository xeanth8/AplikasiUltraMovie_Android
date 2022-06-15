package mobile.uas.kel_15.ultramovie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
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
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        navController = navHostFragment.getNavController();
        BottomNavigationView navigationBarView = findViewById(R.id.bottom_navigation_view);
        NavigationUI.setupWithNavController(navigationBarView, navController);

        // Hide bottom navigation view ketika keyboard muncul
        // Ini menyebabkan status bar jadi putih, masih mencari solusi
        ViewCompat.setOnApplyWindowInsetsListener(navigationBarView.getRootView(), (v, windowInsets) -> {
            boolean imeVisible = windowInsets.isVisible(WindowInsetsCompat.Type.ime());

            // This piece of code makes the bottom navbar always visible when keyboard is not present.
            // Meaning even in loginFragment if the keyboard is not present, the bottom nav will be visible.
            // So this is a workaround for now...
            if (navController.getCurrentDestination().getId() == R.id.loginFragment ||
                navController.getCurrentDestination().getId() == R.id.registerFragment ||
                navController.getCurrentDestination().getId() == R.id.aboutUsFragment) imeVisible = true;

            ViewKt.setVisible(navigationBarView, !imeVisible);

            Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int bottomPadding;
            if (ViewKt.isVisible(navigationBarView)) {
                bottomPadding = systemBars.bottom;
            } else {
                bottomPadding = 0;
            }

            new WindowInsetsCompat.Builder(windowInsets).setInsets(
                    WindowInsetsCompat.Type.systemBars(),
                    Insets.of(0, systemBars.top, 0, systemBars.bottom - bottomPadding)
            ).build();

            return windowInsets;
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