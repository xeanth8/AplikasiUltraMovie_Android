package mobile.uas.kel_15.ultramovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationBarView;

import mobile.uas.kel_15.ultramovie.genre.GenreListFragment;
import mobile.uas.kel_15.ultramovie.movie.MovieListFragment;
import mobile.uas.kel_15.ultramovie.movie.MovieViewFragment;
import mobile.uas.kel_15.ultramovie.writer.WriterListFragment;

public class MainActivity extends AppCompatActivity {
    //    Toolbar toolbar;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
    NavigationBarView navigationBarView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkManager.getInstance(this);

        showFragment(new MovieListFragment());

        navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.setOnItemSelectedListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.navigation_bottom_item_movies) {
                        showFragment(new MovieListFragment());
                    } else if (id == R.id.navigation_bottom_item_genres) {
                        showFragment(new MovieViewFragment());
                    } else if (id == R.id.navigation_bottom_item_writers) {
                        showFragment(new WriterListFragment());
                    } else if (id == R.id.navigation_bottom_item_more) {
                        showFragment(new MoreFragment());
                    }

                    return true;
                }
        );

    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_container, fragment).commit();
    }
}