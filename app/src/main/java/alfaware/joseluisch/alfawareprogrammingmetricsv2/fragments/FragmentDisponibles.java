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

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.R;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.adapters.ResultListAdapter;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.databinding.FragmentDisponiblesBinding;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Result;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.viewmodels.FragmentDisponiblesViewModel;

public class FragmentDisponibles extends Fragment {

    private FragmentDisponiblesViewModel viewModel;
    private FragmentDisponiblesBinding binding;
    private ResultListAdapter adapter;

    private int pageCount = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_disponibles, container, false);
        viewModel = ViewModelProviders.of(this).get(FragmentDisponiblesViewModel.class);
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
        binding.fragmentDisponiblesListview.setAdapter(adapter);

        Result result = new Result();
        result.setPage(pageCount);

        viewModel.getResults(result);
    }

    private void setListeners() {
        binding.fragmentDisponiblesFloatingActionButton.setOnClickListener(view -> {
            loadNewItems();
        });
        binding.fragmentDisponiblesListview.setOnItemClickListener((adapterView, view, i, l) -> {
            Result result = viewModel.getResults().getValue().getResult().getData().get(i);
            viewModel.add(result);
        });
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
            } else {
                //Todo está okay
                adapter.notifyDataSetChanged();
            }

        });
        viewModel.getResult().observe(this, resultData -> {

            Result result = resultData.getData();
            Exception e = resultData.getException();

            if (e != null) {
                e.printStackTrace();
                Snackbar.make(getView(), "Ocurrió un error al agregar a " + result.getName() + " a la base de datos", Snackbar.LENGTH_SHORT).show();
                //Asignar pantalla de error
            } else {
                //Todo está okay
                String s = "Se agregó " + result.getName() + " a la base de datos";
                Snackbar.make(getView(), s, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNewItems() {
        Result result = new Result();
        result.setPage(++pageCount);
        viewModel.getResults(result);
    }

}
