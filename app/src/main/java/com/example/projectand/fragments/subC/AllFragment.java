package com.example.projectand.fragments.subC;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectand.POJO.ItemListRecipe;
import com.example.projectand.POJO.Recipe;
import com.example.projectand.POJO.User;
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

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment implements RecipeAdapter.OnListItemClickListener {

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("recipes");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<ItemListRecipe> items;
    private RecipeAdapter rA;

    public AllFragment() {
        // Required empty public constructor
    }

    public static AllFragment newInstance() {
        return new AllFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all, container, false);

        RecyclerView rv = rootView.findViewById(R.id.rvA);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        items = new ArrayList<>();
        rA = new RecipeAdapter(items, this);
        rv.setAdapter(rA);

        for(ItemListRecipe i: items) Log.d("cool",i.toString());

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
    public void onListItemClick(View v,int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), RecipeActivity.class);
        intent.putExtra("idRecipe",items.get(clickedItemIndex).getIdI());
        startActivity(intent);
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
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            items.clear();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                String nameR = ds.child("nameR").getValue(String.class);
                int imgR = ds.child("imgR").getValue(Integer.class);
                int nbFav = ds.child("nbFav").getValue(Integer.class);
                String nameU = ds.child("nameU").getValue(String.class);
                String idI = ds.getKey();

                items.add(new ItemListRecipe(idI,nameR,nameU,nbFav,imgR));
                rA.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
