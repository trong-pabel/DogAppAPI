package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.DogsViewHolder> {

    private static ArrayList<DogBreed> mListDogsBread;

    public DogsAdapter(ArrayList<DogBreed> mListDogsBread) {
        this.mListDogsBread = mListDogsBread;
    }

    @NonNull
    @Override
    public DogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dogs_item,parent,false);
        return new DogsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsViewHolder holder, int position) {
        DogBreed dog = mListDogsBread.get(position);
        if (dog == null)
            return;
        holder.tvname.setText(dog.getName());
        holder.tvorigin.setText(dog.getOrigin());
        Picasso.get().load(mListDogsBread.get(position).getUrl()).into(holder.imgv);
    }

    @Override
    public int getItemCount() {
        if(mListDogsBread != null)
            return mListDogsBread.size();
        return 0;
    }

    public static class DogsViewHolder extends RecyclerView.ViewHolder{

        private TextView tvname, tvorigin;
        private ImageView imgv;
        public DogsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tv_dogname);
            tvorigin = itemView.findViewById(R.id.tv_origin);
            imgv = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DogBreed dog = mListDogsBread.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dogBreed" , dog);
                    Navigation.findNavController(view).navigate(R.id.detailsFragment,bundle);
                }
            });
        }
    }
}
