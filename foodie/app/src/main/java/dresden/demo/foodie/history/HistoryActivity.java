package dresden.demo.foodie.history;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//public class HistoryActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history);
//    }
//}

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;

import java.util.ArrayList;
/*
    Navigation history activity
 */
public class HistoryActivity extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<HistoryModel> historyModalArrayList;
    private HistoryDBHandler dbHandler;
    private HistoryRVAdapter historyRVAdapter;
    private RecyclerView historyRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // initializing our all variables.
        historyModalArrayList = new ArrayList<>();
        dbHandler = new HistoryDBHandler(HistoryActivity.this);

        // getting our history array
        // list from db handler class.
        historyModalArrayList = dbHandler.readHistory();

        // on below line passing our array lost to our adapter class.
        historyRVAdapter = new HistoryRVAdapter(historyModalArrayList, HistoryActivity.this);
        historyRV = findViewById(R.id.idRVHistory);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryActivity.this, RecyclerView.VERTICAL, false);
        historyRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        historyRV.setAdapter(historyRVAdapter);
    }
}

