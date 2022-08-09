package dresden.demo.foodie.ui.canteen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;

public class CanteenViewAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private Context context;
    private int[] images;
    private String[] placeNames;

    CanteenViewAdapter(Context context, int[] images, String[] placeNames) {
        this.context = context;
        this.images = images;
        this.placeNames = placeNames;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.canteen_recyclerview_item_row, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, int position) {
        holder.placeName.setText(placeNames[position]);
        holder.place.setImageResource(images[position]);
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(context, MenuViewActivity.class);
                    // pass canteen name to next intent
                    i.putExtra("placeName", holder.placeName.getText());
                    context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}

class PlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView place;
    TextView placeName;
    Button menu;

    PlaceViewHolder(View itemView) {
        super(itemView);

        place = itemView.findViewById(R.id.ivPlace);
        placeName = itemView.findViewById(R.id.idCanteenName);
        menu = itemView.findViewById(R.id.btnMenu);
    }
}