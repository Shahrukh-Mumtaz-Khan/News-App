package com.example.newsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.NewsHeadlines;
import com.example.newsapp.UI.DetailsActivity;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener , View.OnClickListener {

    RecyclerView recycler;
    ImageView imageView;
    CustomeAdapter adapter;
    ProgressDialog dialog;

    Button  science,technology,genral,health,business,sports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        science=findViewById(R.id.science);
        science.setOnClickListener(this);

        technology=findViewById(R.id.technology);
        technology.setOnClickListener(this);

        genral=findViewById(R.id.genral);
        genral.setOnClickListener(this);

        health=findViewById(R.id.health);
        health.setOnClickListener(this);

        business=findViewById(R.id.business);
        business.setOnClickListener(this);

        sports=findViewById(R.id.sports);
        sports.setOnClickListener(this);





        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);

    }
    private final OnFetchingDataListener<NewsApiResponse>  listener=new OnFetchingDataListener<NewsApiResponse>() {
        @Override
        public void onFatchData(List<NewsHeadlines> list, String message) {

            showNews(list);
            dialog.dismiss();


        }

        @Override
        public void onError(String message) {

        }
    };
    private void showNews(List<NewsHeadlines>list){
        recycler=findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomeAdapter(this,list,this);
        recycler.setAdapter(adapter);


        //loading page
        dialog=new ProgressDialog(this);
        dialog.setTitle("PLEASE WAIT...");
        dialog.show();
        // full Screen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

//        ZoomableImageView touch = (ZoomableImageView)findViewById(R.id.IMAGEID);
//        touch.setImageBitmap(bitmap);

    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {

        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
                .putExtra("data",  headlines));


    }

    @Override
    public void onClick(View v) {
        Button button=(Button) v;
        String categery=button.getText().toString();
        dialog.setTitle("Fetching news Articles of "+categery);
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,categery,null);


    }
}