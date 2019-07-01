package com.planensas.myapplication.Activities;

import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;
import com.planensas.myapplication.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Uri uri = Uri.parse("https://github.com/qleoz12.png");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.profileImage);
        draweeView.setImageURI(uri);

    }

    @Override
    public int getLayoutId() { return R.layout.activity_about; }
}
