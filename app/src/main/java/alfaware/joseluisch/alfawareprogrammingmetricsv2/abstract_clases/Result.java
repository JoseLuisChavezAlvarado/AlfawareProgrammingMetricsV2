package alfaware.joseluisch.alfawareprogrammingmetricsv2.abstract_clases;

public class Result<T> {

    private DataResult<T, Exception> result = new DataResult<>();

    public DataResult<T, Exception> getResult() {
        return result;
    }

    public void setResult(DataResult<T, Exception> result) {
        this.result = result;
    }
}
