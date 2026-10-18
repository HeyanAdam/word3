package cn.edu.sicnu.cs.heyanjun.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ReturnParamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_return_param);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.return_param_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editReturnInput = findViewById(R.id.edit_return_input);
        Button btnConfirmReturn = findViewById(R.id.btn_confirm_return);
        btnConfirmReturn.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra(MainActivity.EXTRA_RESULT, editReturnInput.getText().toString());
            setResult(RESULT_OK, result);
            finish();
        });
    }
}
