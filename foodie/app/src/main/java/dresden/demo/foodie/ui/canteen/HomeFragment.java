package dresden.demo.foodie.ui.canteen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dresden.demo.foodie.R;
import dresden.demo.foodie.databinding.FragmentCanteenBinding;

public class HomeFragment extends Fragment {

    private FragmentCanteenBinding binding;
    private RecyclerView recyclerView;
    int[] images;
    String[] canteenNames;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_canteen, container, false);

        recyclerView = view.findViewById(R.id.idCanteens);

        images = new int[]{R.drawable.alte, R.drawable.mensa, R.drawable.mensa2};

        canteenNames = new String[]{"Alte Mensa", "Mensa Zeltschlösschen", "Mensa Reichenbachstraße"};

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CanteenViewAdapter myAdapter = new CanteenViewAdapter(this.getContext(), images, canteenNames);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}