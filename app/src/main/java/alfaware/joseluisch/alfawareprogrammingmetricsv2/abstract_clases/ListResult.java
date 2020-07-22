package alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases;

import java.util.List;

public class ListResult<T> {

    private DataResult<List<T>, Exception> result = new DataResult<>();


    public DataResult<List<T>, Exception> getResult() {
        return result;
    }

    public void setResult(DataResult<List<T>, Exception> result) {
        this.result = result;
    }

}
