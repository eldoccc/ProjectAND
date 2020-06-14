package com.example.projectand.fragments.subC;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectand.POJO.ItemListRecipe;
import com.example.projectand.R;
import com.example.projectand.activities.CreateRecipeActivity;
import com.example.projectand.activities.RecipeActivity;
import com.example.projectand.adapters.RecipeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment implements RecipeAdapter.OnListItemClickListener {

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("recipes");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<ItemListRecipe> items = new ArrayList<>();
    private RecipeAdapter rA;

    RecipeAdapter recipeAdapter;

    public DayFragment() {
        // Required empty public constructor
    }

    public static DayFragment newInstance() {
        return new DayFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_day, container, false);

        RecyclerView rv = rootView.findViewById(R.id.rvD);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rA = new RecipeAdapter(items,this);
        rv.setAdapter(rA);
        FloatingActionButton fab = rootView.findViewById(R.id.floatingActionButton);
        if(user != null){
            fab.setVisibility(View.VISIBLE);
        }
        else fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CreateRecipeActivity.class));
            }
        });
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        myRef.addValueEventListener(listener);
    }

    @Override
    public void onStop() {
        super.onStop();
        myRef.removeEventListener(listener);
    }

    private ValueEventListener listener = new ValueEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                if(Objects.equals(ds.child("dateR").getValue(String.class), LocalDate.now().toString())){
                    String nameR = ds.child("nameR").getValue(String.class);
                    int imgR = ds.child("imgR").getValue(Integer.class);
                    int nbFav = ds.child("nbFav").getValue(Integer.class);
                    String nameU = ds.child("nameU").getValue(String.class);
                    String idI = ds.getKey();

                    items.add(new ItemListRecipe(idI,nameR,nameU,nbFav,imgR));
                }
                rA.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @Override
    public void onListItemClick(View v, int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), RecipeActivity.class);
        intent.putExtra("idRecipe",items.get(clickedItemIndex).getIdI());
        startActivity(intent);
    }
}
