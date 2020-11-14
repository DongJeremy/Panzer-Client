package org.cloud.core.widget;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.cloud.core.R;
import org.cloud.core.utils.ImageUtils;

/**
 * FileName: ImageFullScreenActivity
 * Author: Admin
 * Date: 2020/11/13 13:43
 * Description: ImageFullScreenActivity
 */
public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(null);
        }
        String img = getIntent().getStringExtra("img");
        ImageView imageView = findViewById(R.id.imageView);
        ImageUtils.getInstance().display(img, imageView);

        imageView.setOnClickListener(v -> {
            // 注意这里不使用finish
            ActivityCompat.finishAfterTransition(ImageActivity.this);
        });
    }
}
