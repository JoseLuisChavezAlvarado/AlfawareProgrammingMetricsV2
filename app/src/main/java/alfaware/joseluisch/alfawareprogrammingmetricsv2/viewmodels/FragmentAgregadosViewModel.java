package alfaware.joseluisch.alfawareprogrammingmetricsv2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases.DataResult;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases.ListResult;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Result;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.repositories.ResultRepository;

public class FragmentAgregadosViewModel extends ViewModel {

    private MutableLiveData<ListResult<Result>> results = new MutableLiveData<>();

    public MutableLiveData<ListResult<Result>> getResults() {
        if (results.getValue() == null) {
            results.setValue(new ListResult<>());
            results.getValue().setResult(new DataResult<>());
            results.getValue().getResult().setData(new ArrayList<>());
        }

        return results;
    }

    public void getResults(Result result) {
        ResultRepository.getInstance().getFirebase(results, result);
    }

}
