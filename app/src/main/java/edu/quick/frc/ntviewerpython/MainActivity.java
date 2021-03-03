package edu.quick.frc.ntviewerpython;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    nt_utils nt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nt = new nt_utils(this);

        TextView t = findViewById(R.id.Text);
        t.setText(nt.run());
    }
}