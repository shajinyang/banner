package com.example.recycleviewapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private Context mContext;
    private List<Integer> images=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_main);
        images.add(R.mipmap.banner);
        images.add(R.mipmap.banner2);
        images.add(R.mipmap.banner3);
        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());

    }


    private class MyAdapter extends RecyclerView.Adapter{

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if(i==0){
                return new BannerHolder(LayoutInflater.from(mContext).inflate(R.layout.banneritem,viewGroup,false));
            }else {
                return new TvViewHolder(LayoutInflater.from(mContext).inflate(R.layout.content_main,viewGroup,false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if(viewHolder instanceof BannerHolder){
                ((BannerHolder) viewHolder).banner
                         .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                    imageView.setImageResource((Integer) path);
                            }
                        })
                        .setImages(images)
                        .setBannerAnimation(Transformer.DepthPage)
                        .isAutoPlay(true)
                        .setDelayTime(4000)
                        .setIndicatorGravity(BannerConfig.CENTER)
                        .start();
            }
        }

        @Override
        public int getItemCount() {
            return 40;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    private class BannerHolder extends RecyclerView.ViewHolder {
        Banner banner;
        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
        }
    }

    private class TvViewHolder extends RecyclerView.ViewHolder{
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
