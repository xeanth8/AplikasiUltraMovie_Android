package mobile.uas.kel_15.ultramovie;

import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

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
        navigationBarView.setOnItemSelectedListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.navigation_bottom_item_movies) {
                        navController.navigate(R.id.movieListFragment);
                    } else if (id == R.id.navigation_bottom_item_genres) {
                        navController.navigate(R.id.genreListFragment);
                    } else if (id == R.id.navigation_bottom_item_writers) {
                        navController.navigate(R.id.writerListFragment);
                    } else if (id == R.id.navigation_bottom_item_more) {
                        navController.navigate(R.id.moreFragment);
                    }

                    return true;
                }
        );
        navigationBarView.setOnItemReselectedListener(item -> {});
    }
}