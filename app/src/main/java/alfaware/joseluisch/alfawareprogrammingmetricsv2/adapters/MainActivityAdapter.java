package alfaware.joseluisch.alfawareprogrammingmetricsv2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.R;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.databinding.MainActivityAdapterBinding;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Result;

public class MainActivityAdapter extends ArrayAdapter<Result> {

    public MainActivityAdapter(Context context, List<Result> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MainActivityAdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_activity_adapter, null, false);
        Glide.with(parent.getContext()).load(getItem(position).getImage()).into(binding.mainActivityAdapterImageView);
        binding.setResult(getItem(position));
        return binding.getRoot();
    }

}
