package alfaware.joseluisch.alfawareprogrammingmetricsv2.interfaces;

import alfaware.joseluisch.alfawareprogrammingmetricsv2.entities.Character;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetServiceData {

    @GET("/api/character/")
    Call<Character> get(@Query("page") String page);

}
