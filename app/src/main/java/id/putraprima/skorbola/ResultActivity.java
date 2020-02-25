package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MatchActivity.WINNER_KEY;

public class ResultActivity extends AppCompatActivity {
    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winner = findViewById(R.id.textView3);

        winner.setText(getIntent().getStringExtra(WINNER_KEY));

    }
}
