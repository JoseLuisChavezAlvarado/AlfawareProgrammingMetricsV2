package alfaware.joseluisch.alfawareprogrammingmetricsv2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.R;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.adapters.ResultListAdapter;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.databinding.FragmentAgregadosBinding;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.viewmodels.FragmentAgregadosViewModel;

public class FragmentAgregados extends Fragment {

    private FragmentAgregadosViewModel viewModel;
    private FragmentAgregadosBinding binding;
    private ResultListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(FragmentAgregadosViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agregados, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        setListeners();
        setObservers();
    }

    private void init() {
        adapter = new ResultListAdapter(getContext(), viewModel.getResults().getValue().getResult().getData());
        binding.fragmentAgregadosListview.setAdapter(adapter);

        viewModel.getResults(null);
    }

    private void setListeners() {

    }

    private void setObservers() {
        viewModel.getResults().observe(this, resultListResult -> {

            Exception e = resultListResult.getResult().getException();
            List list = resultListResult.getResult().getData();

            if (e != null) {
                e.printStackTrace();
                //Asignar pantalla de error
            } else if (list.isEmpty()) {
                //Asignar pantalla de datos vacíos
                binding.fragmentAgregadosLinearLayout.setVisibility(View.VISIBLE);
            } else {
                //Todo está okay
                adapter.notifyDataSetChanged();
                binding.fragmentAgregadosLinearLayout.setVisibility(View.GONE);
            }

        });
    }

}
