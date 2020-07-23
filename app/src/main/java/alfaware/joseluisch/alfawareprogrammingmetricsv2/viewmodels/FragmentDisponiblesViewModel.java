package alfaware.joseluisch.alfawareprogrammingmetricsv2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases.DataResult;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases.ListResult;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Result;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.repositories.ResultRepository;

public class FragmentDisponiblesViewModel extends ViewModel {

    private MutableLiveData<ListResult<Result>> results = new MutableLiveData<>();
    private MutableLiveData<DataResult<Result, Exception>> result = new MutableLiveData<>();

    public MutableLiveData<ListResult<Result>> getResults() {
        if (results.getValue() == null) {
            results.setValue(new ListResult<>());
            results.getValue().setResult(new DataResult<>());
            results.getValue().getResult().setData(new ArrayList<>());
        }

        return results;
    }

    public MutableLiveData<DataResult<Result, Exception>> getResult() {
        if (result.getValue() == null) {
            result.setValue(new DataResult<>());
            result.getValue().setData(new Result());
        }
        return result;
    }

    public void getResults(Result result) {
        ResultRepository.getInstance().get(results, result);
    }

    public void add(Result result) {
        ResultRepository.getInstance().add(this.result, result);
    }

}
