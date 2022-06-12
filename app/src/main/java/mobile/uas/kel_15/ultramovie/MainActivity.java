package mobile.uas.kel_15.ultramovie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewKt;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mobile.uas.kel_15.ultramovie.movie.MovieViewFragment;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    private int selectedIndex;
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
        ViewCompat.setOnApplyWindowInsetsListener(navigationBarView.getRootView(), (v, insets) -> {
            boolean imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime());
            ViewKt.setVisible(navigationBarView, !imeVisible);

//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            int bottomPadding;
//            if (ViewKt.isVisible(navigationBarView)) {
//                bottomPadding = systemBars.bottom;
//            } else {
//                bottomPadding = 0;
//            }
//
//            new WindowInsetsCompat.Builder(insets).setInsets(
//                    WindowInsetsCompat.Type.systemBars(),
//                    Insets.of(0, systemBars.top, 0, systemBars.bottom - bottomPadding)
//            ).build();
            return insets;
        });

        // For user level validation later
        //        botto mNavigationView.getMenu().findItem(R.id.navigation_bottom_item_writers).setVisible(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navController = Navigation.findNavController(this,
                R.id.nav_host_fragment_container);

        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}