package dresden.demo.foodie.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;
import dresden.demo.foodie.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private FragmentOrderBinding binding;
    private ArrayList<OrderModel> orderList;
    private OrderDBHandler dbHandler;
    private OrderRVAdapter rvAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.idOrders);

        orderList = new ArrayList<>();
        dbHandler = new OrderDBHandler(this.getContext());

        orderList = dbHandler.readOrder();
        rvAdapter = new OrderRVAdapter(orderList, this.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rvAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}