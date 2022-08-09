package dresden.demo.foodie.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import dresden.demo.foodie.MainActivity;
import dresden.demo.foodie.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class PayOkActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payok);

        textView = findViewById(R.id.text_payok);
        textView.setText("Order placed!");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(PayOkActivity.this, MainActivity.class);
                i.putExtra("activity", "ordered");
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}