package cn.edu.sicnu.cs.heyanjun.myapplication3;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TimerActivity extends AppCompatActivity {

    private static final String STATE_RUNNING = "state_running";
    private static final String STATE_BASE = "state_base";

    private Chronometer chronometer;
    private Button btnStartTimer;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.timer_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chronometer = findViewById(R.id.chronometer);
        btnStartTimer = findViewById(R.id.btn_start_timer);

        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean(STATE_RUNNING, false);
            long base = savedInstanceState.getLong(STATE_BASE, SystemClock.elapsedRealtime());
            chronometer.setBase(base);
            if (running) {
                chronometer.start();
                btnStartTimer.setEnabled(false);
            }
        }

        btnStartTimer.setOnClickListener(v -> {
            if (!running) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                running = true;
                btnStartTimer.setEnabled(false);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RUNNING, running);
        outState.putLong(STATE_BASE, chronometer.getBase());
    }
}
