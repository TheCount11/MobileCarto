package dresden.demo.foodie.ui.canteen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;

import android.content.Intent;
import android.os.Bundle;

public class MenuViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    int[] images;
    String[] foodNames;
    String[] foodPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.idMenuview);

        Intent i = getIntent();
        String placeName = i.getStringExtra("placeName");
        if (placeName.equals("Alte Mensa")) {
            images = new int[]{R.drawable.m11, R.drawable.m12, R.drawable.m13};
            foodNames = new String[]{"Szegediner Gulasch mit Hefeknödeln",
                    "Zwei Eier in Senfsoße mit Buttererbsen und Kartoffeln",
                    "Kartoffelpfanne mit Wirsing, Waldpilzen und Walnüssen,dazu vegane Kräutersoße"};
            foodPrices = new String[]{"2.35", "2.45", "2.65"};
        }
        if (placeName.equals("Mensa Zeltschlösschen")) {
            images = new int[]{R.drawable.m21, R.drawable.m22};
            foodNames = new String[]{"Kasslerückenstaek mit Bratensoße, dazu glasierte Honigmöhren und gebratene Schupfnudeln",
                    "Gabelspaghetti mit Basilikumsoße und getrockneten Tomaten dazu geribener Pizzaschmelzkäse"};
            foodPrices = new String[]{"2.89", "2.35"};
        }
        if (placeName.equals("Mensa Reichenbachstraße")) {
            images = new int[]{R.drawable.m31, R.drawable.m32};
            foodNames = new String[]{"Kasslerückenstaek mit Bratensoße, dazu glasierte Honigmöhren und gebratene Schupfnudeln",
                    "Gabelspaghetti mit Basilikumsoße und getrockneten Tomaten dazu geribener Pizzaschmelzkäse"};
            foodPrices = new String[]{"2.89", "2.35"};
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MenuViewActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        MenuViewAdapter myAdapter = new MenuViewAdapter(MenuViewActivity.this, images, placeName, foodNames, foodPrices);
        recyclerView.setAdapter(myAdapter);
    }

}