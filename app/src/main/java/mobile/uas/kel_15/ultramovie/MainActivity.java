package mobile.uas.kel_15.ultramovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

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

        showFragment(new MovieFragment());

        navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.setOnItemSelectedListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.navigation_bottom_item_movies) {
                        showFragment(new MovieFragment());
                    } else if (id == R.id.navigation_bottom_item_genres) {
                        showFragment(new GenreFragment());
                    } else if (id == R.id.navigation_bottom_item_writers) {
                        showFragment(new WriterFragment());
                    } else if (id == R.id.navigation_bottom_item_more) {
                        showFragment(new MoreFragment());
                    }

                    return true;
                }
        );

    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_main_content, fragment).commit();
    }
}