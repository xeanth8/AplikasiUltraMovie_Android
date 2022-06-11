package mobile.uas.kel_15.ultramovie;

import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        NavController navController = navHostFragment.getNavController();
//        setupActionBarWithNavController(this, navController);

        BottomNavigationView navigationBarView;
        navigationBarView = findViewById(R.id.bottom_navigation_view);
        NavigationUI.setupWithNavController(navigationBarView, navController);

        navigationBarView.setOnItemReselectedListener(item -> {});

        // For user level validation later
        //        bottomNavigationView.getMenu().findItem(R.id.navigation_bottom_item_writers).setVisible(false);

    }
}