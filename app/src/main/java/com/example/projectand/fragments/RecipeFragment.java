package com.example.projectand.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.projectand.POJO.ItemListRecipe;
import com.example.projectand.R;
import com.example.projectand.activities.RecipeActivity;
import com.example.projectand.adapters.RecipeAdapter;
import com.example.projectand.viewmodels.RecipeViewModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment implements RecipeAdapter.OnListItemClickListener {


    private RecipeAdapter rA;
    private RecyclerView rv;
    private RecipeViewModel rvm;
    private TextView txtSearch;
    private ImageView imgSearch;
    private ArrayList<ItemListRecipe> itemList = new ArrayList<>();


    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance(){
        return new RecipeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        assert getArguments() != null;
        boolean searchState = getArguments().getBoolean("searchState");
        Log.d("st", String.valueOf(searchState));
        txtSearch = rootView.findViewById(R.id.txtSearch);
        imgSearch = rootView.findViewById(R.id.imgSearch);
        rv = rootView.findViewById(R.id.rvR);
        if(searchState){
            txtSearch.setVisibility(View.GONE);
            imgSearch.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
        else{
            txtSearch.setVisibility(View.VISIBLE);
            imgSearch.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        }
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvm = new ViewModelProvider(this).get(RecipeViewModel.class);
        rvm.updateRecipe(getArguments().getString("searchR"));
        rA = new RecipeAdapter(itemList,RecipeFragment.this);
        rv.setAdapter(rA);
        rvm.getRecipe().observe(requireActivity(), new Observer<ArrayList<ItemListRecipe>>() {
            @Override
            public void onChanged(ArrayList<ItemListRecipe> items) {
                Log.d("LA LISTE",items.toString());
                itemList = items;
                rA.notifyDataSetChanged();
            }
        });

        return rootView;  //Todo adding the recipes from the api with image loading (glide)
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onListItemClick(View v, int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), RecipeActivity.class);
        intent.putExtra("idRecipeApi",itemList.get(clickedItemIndex).getIdI());
        startActivity(intent);
    }
}
