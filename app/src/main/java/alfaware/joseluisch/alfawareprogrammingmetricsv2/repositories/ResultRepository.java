package alfaware.joseluisch.alfawareprogrammingmetricsv2.repositories;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases.DataResult;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases.ListResult;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Character;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Result;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.firebase.FirebaseReference;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.interfaces.GetServiceData;
import alfaware.joseluisch.alfawareprogrammingmetricsv2.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultRepository {

    private static ResultRepository instance;

    public static ResultRepository getInstance() {
        if (instance == null) {
            instance = new ResultRepository();
        }
        return instance;
    }

    public void get(final MutableLiveData<ListResult<Result>> liveData, Result result) {

        GetServiceData service = RetrofitClientInstance.getRetrofitInstance().create(GetServiceData.class);

        Call<Character> call = service.get(String.valueOf(result.getPage()));
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if (response.isSuccessful()) {
                    Character body = response.body();
                    List<Result> list = body.getResults();

                    //liveData.getValue().getResult().getData().clear();
                    liveData.getValue().getResult().getData().addAll(list);
                    liveData.postValue(liveData.getValue());
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                liveData.getValue().getResult().setException(new Exception(t.getMessage()));
                liveData.postValue(liveData.getValue());
            }
        });
    }

    public void add(MutableLiveData<DataResult<Result, Exception>> liveData, Result result) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(FirebaseReference.RESULT);
        reference.child(result.toString()).setValue(result).addOnSuccessListener(aVoid -> {
            liveData.getValue().setData(result);
            liveData.postValue(liveData.getValue());
        }).addOnFailureListener(e -> {
            liveData.getValue().setData(result);
            liveData.getValue().setException(e);
            liveData.postValue(liveData.getValue());
        });
    }


}
