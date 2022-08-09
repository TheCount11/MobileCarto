package dresden.demo.foodie.history;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;

import java.util.ArrayList;

public class HistoryRVAdapter extends RecyclerView.Adapter<HistoryRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<HistoryModel> historyModalArrayList;
    private Context context;

    public HistoryRVAdapter(ArrayList<HistoryModel> historyModalArrayList, Context context) {
        this.historyModalArrayList = historyModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        HistoryModel modal = historyModalArrayList.get(position);
        holder.time.setText(modal.getTime());
        holder.source.setText(modal.getSource());
        holder.destination.setText(modal.getDestination());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return historyModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView time, source, destination;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            time = itemView.findViewById(R.id.idHistoryTime);
            source = itemView.findViewById(R.id.idHistorySource);
            destination = itemView.findViewById(R.id.idHistoryDest);
        }
    }
}

