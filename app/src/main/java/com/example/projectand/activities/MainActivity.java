package com.example.projectand.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.projectand.POJO.Recipe;
import com.example.projectand.R;
import com.example.projectand.fragments.CommunityFragment;
import com.example.projectand.fragments.HomeFragment;
import com.example.projectand.fragments.ProfileFragment;
import com.example.projectand.fragments.RecipeFragment;
import com.example.projectand.viewmodels.RecipeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Fragment selectedFragment;
    Boolean state = false;
    Boolean isRecipeFragment = false;
    RecipeViewModel rvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView botNav = findViewById(R.id.bottom_nav);
        botNav.setOnNavigationItemSelectedListener(navListener);
        Bundle extras = getIntent().getExtras();
        rvm = new ViewModelProvider(this).get(RecipeViewModel.class);
        if(extras != null) {
            String frag = extras.getString("OpenFragment", null);
            String fragmentPackage = "com.example.projectand.fragments";
            try {
                selectedFragment = (Fragment) Class.forName(fragmentPackage+"."+frag).newInstance();
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else selectedFragment = HomeFragment.newInstance();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem itemS = menu.findItem(R.id.action_search);
        if(state) itemS.setVisible(true);
        else itemS.setVisible(false);

        SearchView searchView = (SearchView) itemS.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSupportFragmentManager().beginTransaction().remove(selectedFragment).commit();
                if(isRecipeFragment) selectedFragment = RecipeFragment.newInstance();
                else selectedFragment = CommunityFragment.newInstance();

                Bundle bundle = new Bundle();
                bundle.putBoolean("searchState",true);
                bundle.putString("searchR",query);

                selectedFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                //rvm.updateRecipe(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_help:
                //aaaaaaa
                return true;
            case R.id.action_settings:
                //aaaaaaa
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment =  HomeFragment.newInstance();
                            state = false;
                            break;
                        case R.id.nav_recipe:
                            selectedFragment = RecipeFragment.newInstance();
                            isRecipeFragment = true;
                            state = true;
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("searchState",false);
                            selectedFragment.setArguments(bundle);
                            break;
                        case R.id.nav_community:
                            selectedFragment = CommunityFragment.newInstance();
                            isRecipeFragment = false;
                            state = true;
                            break;
                        case R.id.nav_profile:
                            selectedFragment = ProfileFragment.newInstance();
                            state = false;
                            break;
                    }
                    invalidateOptionsMenu();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, selectedFragment).commit();
                    return true;
                }

            };


}
