package cn.edu.sicnu.cs.heyanjun.myapplication3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "extra_text";
    public static final String EXTRA_RESULT = "extra_result";
    private static final String STATE_PHOTO_URI = "state_photo_uri";

    private EditText editMainInput;
    private ImageView imagePhoto;
    private Uri photoUri;

    private final ActivityResultLauncher<Intent> returnParamLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String text = result.getData().getStringExtra(EXTRA_RESULT);
                    if (text != null) {
                        editMainInput.setText(text);
                    }
                }
            });

    private final ActivityResultLauncher<Intent> cameraLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null
                        && result.getData().getData() != null) {
                    showPhoto(result.getData().getData());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editMainInput = findViewById(R.id.edit_main_input);
        imagePhoto = findViewById(R.id.image_photo);
        Button btnPassParam = findViewById(R.id.btn_pass_param);
        Button btnReturnParam = findViewById(R.id.btn_return_param);
        Button btnTakePhoto = findViewById(R.id.btn_take_photo);
        Button btnTimer = findViewById(R.id.btn_timer);

        if (savedInstanceState != null) {
            String uriString = savedInstanceState.getString(STATE_PHOTO_URI);
            if (uriString != null) {
                showPhoto(Uri.parse(uriString));
            }
        }

        btnPassParam.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisplayParamActivity.class);
            intent.putExtra(EXTRA_TEXT, editMainInput.getText().toString());
            startActivity(intent);
        });

        btnReturnParam.setOnClickListener(v ->
                returnParamLauncher.launch(new Intent(this, ReturnParamActivity.class)));

        btnTakePhoto.setOnClickListener(v ->
                cameraLauncher.launch(new Intent(this, CameraActivity.class)));

        btnTimer.setOnClickListener(v ->
                startActivity(new Intent(this, TimerActivity.class)));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (photoUri != null) {
            outState.putString(STATE_PHOTO_URI, photoUri.toString());
        }
    }

    private void showPhoto(Uri uri) {
        photoUri = uri;
        imagePhoto.setVisibility(View.VISIBLE);
        imagePhoto.setImageURI(uri);
    }
}
