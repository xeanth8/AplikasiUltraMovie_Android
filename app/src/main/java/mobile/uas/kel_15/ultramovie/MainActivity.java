package mobile.uas.kel_15.ultramovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

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
                        Toast.makeText(MainActivity.this, "Movies", Toast.LENGTH_SHORT).show();
                        showFragment(new MovieFragment());
                    } else if (id == R.id.navigation_bottom_item_genres) {
                        Toast.makeText(MainActivity.this, "Genres", Toast.LENGTH_SHORT).show();
                        showFragment(new GenreFragment());
//                    Intent intent = new Intent(MainActivity.this, GenreActivity.class);
//                    startActivity(intent);
                    } else if (id == R.id.navigation_bottom_item_writers) {
                        Toast.makeText(MainActivity.this, "Writers", Toast.LENGTH_SHORT).show();
                        showFragment(new WriterFragment());
                    } else if (id == R.id.navigation_bottom_item_more) {
                        Toast.makeText(MainActivity.this, "More", Toast.LENGTH_SHORT).show();
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


//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.navigationView);
//
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
//                this,
//                drawerLayout,
//                toolbar,
//                R.string.openNavDrawer,
//                R.string.closeNavDrawer
//        );
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);



//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
