package com.example.projectand.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectand.POJO.Favorite;
import com.example.projectand.POJO.Recipe;
import com.example.projectand.R;
import com.example.projectand.viewmodels.FavoriteViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecipeActivity extends AppCompatActivity {

    FavoriteViewModel fVm;
    String idRecipe;
    private DatabaseReference myRef;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private TextView tvTitle;
    private TextView tvAuthor;
    private ImageView imgRecipe;
    private TextView tvDesc;
    private String key;
    boolean test = false;
    boolean isFromApi = false;
    int nbFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        imgRecipe = findViewById(R.id.imgRecipe);
        tvDesc = findViewById(R.id.tvDesc);
        Bundle extras = getIntent().getExtras();
        assert extras != null;

        if(extras.getString("idRecipeApi") != null){
            isFromApi = true;
        }
        idRecipe = extras.getString("idRecipe");
        myRef = FirebaseDatabase.getInstance().getReference("recipes/"+idRecipe);
        fVm = new ViewModelProvider(this).get(FavoriteViewModel.class);
        fVm.getAllFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                for(Favorite fav : favorites) {
                    assert user != null;
                    if (fav.getIdRecipe().equals(key) && user.getUid().equals(fav.getIdUser()))
                        test = true;
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myRef.removeEventListener(listener);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.action_help).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);
        if(user!=null || !isFromApi) menu.findItem(R.id.action_favorite).setVisible(true);
        return true;
    }

    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("recipes");
            assert user != null;
                if(test){
                    fVm.delete(new Favorite(user.getUid(), idRecipe, 1));
                    item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                    myRef.child(idRecipe).child("nbFav").setValue(nbFav-1);
                    test = false;
                }
                else{
                    fVm.insert(new Favorite(user.getUid(), idRecipe, 1));
                    item.setIcon(R.drawable.ic_favorite_white_24dp);
                    myRef.child(idRecipe).child("nbFav").setValue(nbFav+1);
                    test = true;
                }
        }
        return true;
    }

    private ValueEventListener  listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Recipe recipe = dataSnapshot.getValue(Recipe.class);
            assert recipe != null;
            tvTitle.setText(recipe.getNameR());
            tvAuthor.setText(recipe.getNameU());
            imgRecipe.setImageResource(recipe.getImgR());
            tvDesc.setText(recipe.getDescR());
            nbFav = recipe.getNbFav();
            key = dataSnapshot.getKey();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


}
