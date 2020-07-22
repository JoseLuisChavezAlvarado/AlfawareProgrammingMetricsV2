package alfaware.joseluisch.alfawareprogrammingmetricsv2.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Result implements Serializable {

    private String id;
    private String name;
    private String image;

    private Integer page;
    private Boolean selected;

    public Result() {
    }

    public Result(String id) {
        this.id = id;
    }

    public Result(String id, String name, String image, Integer page) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.page = page;
    }

    public Result(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @NonNull
    @Override
    public String toString() {
        return id;
    }
}
