package dresden.demo.foodie.ui.canteen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;
import dresden.demo.foodie.ui.PayOkActivity;
import dresden.demo.foodie.ui.TopupActivity;
import dresden.demo.foodie.ui.order.OrderDBHandler;
import dresden.demo.foodie.ui.profile.BalanceDBHandler;

public class MenuViewAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private Context context;
    private int[] images;
    private String placeName;
    private String[] foodNames;
    private String[] foodPrices;
    private OrderDBHandler odbHandler;
    private BalanceDBHandler bdbHandler;

    MenuViewAdapter(Context context, int[] images, String placeName, String[] foodNames, String[] foodPrices) {
        this.context = context;
        this.images = images;
        this.placeName = placeName;
        this.foodNames = foodNames;
        this.foodPrices = foodPrices;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_recyclerview_item_row, parent, false);
        odbHandler = new OrderDBHandler(this.context);
        bdbHandler = new BalanceDBHandler(this.context);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, int position) {
        holder.foodName.setText(foodNames[position]);
        holder.foodPrice.setText(foodPrices[position]);
        holder.placeImg.setImageResource(images[position]);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View view) {
                double original = bdbHandler.readBalance();
                double price = Double.parseDouble(holder.foodPrice.getText().toString());

                // compare balance and food price
                if (original > price) {
                    bdbHandler.reduceBalance(price);
                    Intent i = new Intent(context, PayOkActivity.class);
                    context.startActivity(i);
                    recordOrder(LocalDateTime.now().toString(),
                            holder.foodName.getText().toString(),
                            holder.foodPrice.getText().toString(),
                            placeName);
                } else {
                    Intent i = new Intent(context, TopupActivity.class);
                    context.startActivity(i);
                }

            }
        });

    }

    private void recordOrder(String time, String item, String money, String place) {
        odbHandler.addNewOrder(time, item, money, place);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}

class MenuViewHolder extends RecyclerView.ViewHolder {

    ImageView placeImg;
    TextView foodName;
    TextView foodPrice;
    Button share;

    MenuViewHolder(View itemView) {
        super(itemView);

        placeImg = itemView.findViewById(R.id.ivFoodImg);
        foodName = itemView.findViewById(R.id.idFoodName);
        foodPrice = itemView.findViewById(R.id.idFoodPrice);
        share = itemView.findViewById(R.id.btnOrder);
    }
}