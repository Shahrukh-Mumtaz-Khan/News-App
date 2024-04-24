package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomeAdapter extends RecyclerView.Adapter<CustomViewHolder> {
  private   Context context;
  private SelectListener listener;
    private List<NewsHeadlines> headlines;

    public CustomeAdapter(Context context, List<NewsHeadlines> headlines,SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener=listener;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_list_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position ) {

        animation(holder.img_headline);

        holder.text_title.setText(headlines.get(position).getTitle());
   holder.text_source.setText(headlines.get(position).getSource().getName());

if (headlines.get(position).getUrlToImage()!=null){
    Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);

}
holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        listener.OnNewsClicked(headlines.get(position));

    }
});
    }
    private void animation(View view){
        Animation animation= AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        view.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
