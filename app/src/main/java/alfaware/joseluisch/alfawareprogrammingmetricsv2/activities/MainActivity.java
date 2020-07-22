package alfaware.joseluisch.alfawareprogrammingmetricsv2.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.R;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.adapters.MainActivityAdapter;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.databinding.MainActivityBinding;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Result;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private MainActivityBinding binding;
    private MainActivityAdapter adapter;

    private int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.main_activity);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        init();
        setListeners();
        setObservers();
    }

    private void init() {
        adapter = new MainActivityAdapter(getApplicationContext(), viewModel.getResults().getValue().getResult().getData());
        binding.mainActivityListview.setAdapter(adapter);

        Result result = new Result();
        result.setPage(pageCount);

        viewModel.getResults(result);
    }

    private void setListeners() {
        binding.mainActivityFloatingActionButton.setOnClickListener(view -> {
            loadNewItems();
        });
        binding.mainActivityListview.setOnItemClickListener((adapterView, view, i, l) -> {
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
                Snackbar.make(getCurrentFocus(), "Ocurrió un error al agregar a " + result.getName() + " a la base de datos", Snackbar.LENGTH_SHORT).show();
                //Asignar pantalla de error
            } else {
                //Todo está okay
                String s = "Se agregó " + result.getName() + " a la base de datos";
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //==============================================================================================

    private void loadNewItems() {
        Result result = new Result();
        result.setPage(++pageCount);
        viewModel.getResults(result);
    }

}
