package com.trilogy.libraryedgeservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Isbns {
    private List<String> list = new ArrayList<>();
    private String day;

    public Isbns() {
    }

    public Isbns(List<String> list, String day) {
        this.list = list;
        this.day = day;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Isbns isbns = (Isbns) o;
        return list.equals(isbns.list) &&
                day.equals(isbns.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, day);
    }
}
