package com.example.projectand.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectand.POJO.ItemListRecipe;
import com.example.projectand.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private ArrayList<ItemListRecipe> list;
    final private OnListItemClickListener mOnListItemClickListener;


    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_recipe, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        holder.imgR.setImageResource(list.get(position).getImgRessource());
        holder.tvNbFav.setText(String.valueOf(list.get(position).getNbFav()));
        holder.tvAuthor.setText(list.get(position).getTextAuthor());
        holder.tvName.setText(list.get(position).getTextName());
    }


    public int getItemCount() {
        return list.size();
    }

    public RecipeAdapter(ArrayList<ItemListRecipe> listI,OnListItemClickListener listener){
        list = listI;
        mOnListItemClickListener = listener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgR;
        TextView tvName;
        TextView tvAuthor;
        TextView tvNbFav;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgR = itemView.findViewById(R.id.imgRecipe);
            tvName = itemView.findViewById(R.id.tvName);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvNbFav = itemView.findViewById(R.id.tvFavnb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(v,getAdapterPosition());
        }




    }
    public interface OnListItemClickListener {
        void onListItemClick(View v,int clickedItemIndex);
    }
}
