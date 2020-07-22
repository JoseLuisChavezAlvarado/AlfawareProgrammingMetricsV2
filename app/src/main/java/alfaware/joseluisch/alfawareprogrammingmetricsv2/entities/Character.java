package alfaware.joseluisch.alfawareprogrammingmetricsv2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Character implements Serializable {

    Info info;
    List<Result> results = new ArrayList<>();

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
