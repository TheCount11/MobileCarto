package dresden.demo.foodie.ui.profile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import dresden.demo.foodie.MainActivity;
import dresden.demo.foodie.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DepositActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    BalanceDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.topupbalance);
        button = findViewById(R.id.btnTopupbalance);
        dbHandler = new BalanceDBHandler(DepositActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.addBalance(Integer.valueOf(editText.getText().toString()));
                Intent i = new Intent(DepositActivity.this, MainActivity.class);
                i.putExtra("activity", "topup");
                startActivity(i);
            }
        });

    }
}