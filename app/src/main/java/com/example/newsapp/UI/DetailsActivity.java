package com.example.newsapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.newsapp.Models.NewsHeadlines;
import com.example.newsapp.R;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

public class

DetailsActivity extends AppCompatActivity {
NewsHeadlines headlines;
ZoomageView img_news;
TextView text_details,text_detail_time,text_detail_title,text_details_content,text_detail_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");

        text_details=findViewById(R.id.text_details);
        text_detail_author=findViewById(R.id.text_detail_author);
        text_detail_title=findViewById(R.id.text_detail_title);
        text_details_content=findViewById(R.id.text_details_content);
        text_detail_time=findViewById(R.id.text_detail_time);
        img_news=findViewById(R.id.img_news);


        text_detail_title.setText(headlines.getTitle());
        text_detail_author.setText(headlines.getAuthor());
        text_detail_time.setText(headlines.getPublishedAt());
        text_details.setText(headlines.getDescription());
        text_details_content.setText(headlines.getContent());

        Picasso.get().load(headlines.getUrlToImage()).into(img_news);

        Animation animation= AnimationUtils.loadAnimation(DetailsActivity.this,R.anim.animation);
        img_news.startAnimation(animation);


        Animation title= AnimationUtils.loadAnimation(DetailsActivity.this,R.anim.animation);
        text_detail_title.startAnimation(animation);
    }
}