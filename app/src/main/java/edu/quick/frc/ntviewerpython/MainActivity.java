package edu.quick.frc.ntviewerpython;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    nt_utils nt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nt = new nt_utils(this);

        TextView t = findViewById(R.id.Text);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            t.setText(nt.getTables());
        });
    }
}