package org.cloud.panzer.ui.common;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.panzer.R;

public class ImageFullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(null);
        }
        String img = getIntent().getStringExtra("img");
        ImageView imageView = findViewById(R.id.imageView);
        BaseImageLoader.getInstance().display(img, imageView);

        imageView.setOnClickListener(v -> {
            // 注意这里不使用finish
            ActivityCompat.finishAfterTransition(ImageFullScreenActivity.this);
        });
    }
}