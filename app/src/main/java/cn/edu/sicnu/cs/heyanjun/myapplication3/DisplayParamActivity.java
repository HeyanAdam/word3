package cn.edu.sicnu.cs.heyanjun.myapplication3;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayParamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_param);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.display_param_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textReceivedParam = findViewById(R.id.text_received_param);
        String extra = getIntent().getStringExtra(MainActivity.EXTRA_TEXT);
        if (TextUtils.isEmpty(extra)) {
            textReceivedParam.setText(R.string.display_param_empty);
        } else {
            textReceivedParam.setText(extra);
        }
    }
}
