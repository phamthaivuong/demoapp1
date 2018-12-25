package com.example.phamthaivuong.demoapp1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phamthaivuong.demoapp1.Model.JAVModel;
import com.facebook.drawee.view.SimpleDraweeView;

public class DetailActivity extends AppCompatActivity {
    TextView id , name ,slug ,total_videos,shortname,category_url;
    SimpleDraweeView cover_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = (TextView)findViewById(R.id.id_detail);
        name = (TextView)findViewById(R.id.name_detail);
        slug = (TextView)findViewById(R.id.slug_detail);
        total_videos = (TextView)findViewById(R.id.total_videos_detail);
        shortname = (TextView)findViewById(R.id.shortname_detail);
        category_url = (TextView)findViewById(R.id.category_url_detail);
        cover_url = (SimpleDraweeView)findViewById(R.id.my_iamge_detail);

        Intent intent = getIntent();
        if (intent != null){
            JAVModel javModel = (JAVModel) intent.getSerializableExtra("JAV");
            id.setText(javModel.getcHID());
            name.setText(javModel.getName());
            slug.setText(javModel.getSlug());
            total_videos.setText(String.valueOf(javModel.getTotalVideos()));
            shortname.setText(javModel.getShortname());
            category_url.setText(javModel.getCategoryUrl());
            cover_url.setImageURI( Uri.parse(javModel.getCoverUrl()));

        }
    }
}
