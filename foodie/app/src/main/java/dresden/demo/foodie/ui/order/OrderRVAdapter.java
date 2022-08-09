package dresden.demo.foodie.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.MapsActivity;
import dresden.demo.foodie.R;

public class OrderRVAdapter extends RecyclerView.Adapter<OrderRVAdapter.ViewHolder> {
    // variable for our array list and context
    private ArrayList<OrderModel> courseModalArrayList;
    private Context context;

    // constructor
    public OrderRVAdapter(ArrayList<OrderModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRVAdapter.ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        OrderModel modal = courseModalArrayList.get(position);
        holder.orderTime.setText(modal.getTime());
        holder.orderId.setText("Order ID: " + modal.getId());
        holder.orderItem.setText(modal.getItem());
        holder.orderMoney.setText(modal.getMoney() + " $");
        holder.orderPlace.setText(modal.getPlace());

        holder.navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View view) {
                Intent i = new Intent(context, MapsActivity.class);
                // pass canteen name to navigation page
                i.putExtra("destination", modal.getPlace());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView orderTime, orderId, orderItem, orderMoney, orderPlace;
        private Button navigateButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            orderTime = itemView.findViewById(R.id.idOrderTime);
            orderId = itemView.findViewById(R.id.idOrderId);
            orderItem = itemView.findViewById(R.id.idOrderItem);
            orderMoney = itemView.findViewById(R.id.idOrderMoney);
            orderPlace = itemView.findViewById(R.id.idOrderPlace);
            navigateButton = itemView.findViewById(R.id.btnNavigate);
        }
    }
}
