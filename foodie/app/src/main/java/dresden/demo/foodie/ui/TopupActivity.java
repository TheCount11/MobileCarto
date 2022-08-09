package dresden.demo.foodie.ui;

import androidx.appcompat.app.AppCompatActivity;
import dresden.demo.foodie.MainActivity;
import dresden.demo.foodie.R;
import dresden.demo.foodie.ui.profile.BalanceDBHandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class TopupActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        textView = findViewById(R.id.text_topup);
        textView.setText("Please top up!");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(TopupActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}