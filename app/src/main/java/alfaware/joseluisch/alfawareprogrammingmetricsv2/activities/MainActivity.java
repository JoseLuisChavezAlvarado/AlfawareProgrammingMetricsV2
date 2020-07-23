package alfaware.joseluisch.alfawareprogrammingmetricsv2.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.R;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.adapters.ViewPagerAdapter;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.databinding.MainActivityBinding;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.fragments.FragmentAgregados;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.fragments.FragmentDisponibles;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.main_activity);

        init();
        setListeners();
        setObservers();
    }

    //==============================================================================================

    private void init() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentDisponibles(), "Disponibles");
        adapter.addFragment(new FragmentAgregados(), "Agregados");
        binding.mainActivityViewPager.setAdapter(adapter);
        binding.mainActivityTabLayout.setupWithViewPager(binding.mainActivityViewPager);
    }

    private void setListeners() {
    }

    private void setObservers() {
    }

    //==============================================================================================

}
