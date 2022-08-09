package dresden.demo.foodie.ui.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import dresden.demo.foodie.R;
import dresden.demo.foodie.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    TextView textView;
    ImageView mIcon;
    Button mFollow;
    BalanceDBHandler dbHandler;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dbHandler = new BalanceDBHandler(this.getContext());
        double balance = dbHandler.readBalance();

        mIcon = binding.ivProfile;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.panda);
        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        mDrawable.setCircular(true);
        mDrawable.setColorFilter(ContextCompat.getColor(this.getContext(), R.color.black), PorterDuff.Mode.DST_OVER);
        mIcon.setImageDrawable(mDrawable);

        textView = binding.textBalance;
        textView.setText(balance + " $");

        mFollow = binding.btnFollow;
        mFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), DepositActivity.class);
                startActivity(i);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}