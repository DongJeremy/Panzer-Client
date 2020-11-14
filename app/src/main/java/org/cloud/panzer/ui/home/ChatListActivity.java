package org.cloud.panzer.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.cloud.core.utils.AppUtils;
import org.cloud.core.utils.Utils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;

public class ChatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list_view);
        TextView version = findViewById(R.id.textView);
        version.setText(AppUtils.getAppVersionName(Utils.getContext()));
    }
}