package mobile.uas.kel_15.ultramovie;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewKt;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

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

        navigationBarView.setOnItemReselectedListener(item -> {
            navController.popBackStack(item.getItemId(),false);
        });


        // Hide bottom navigation view ketika keyboard muncul
        // Ini menyebabkan status bar jadi putih, masih mencari solusi
        ViewCompat.setOnApplyWindowInsetsListener(navigationBarView.getRootView(), (v, insets) -> {
            boolean imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime());
            ViewKt.setVisible(navigationBarView, !imeVisible);
            return insets;
        });

        // For user level validation later
        //        botto mNavigationView.getMenu().findItem(R.id.navigation_bottom_item_writers).setVisible(false);

    }
}