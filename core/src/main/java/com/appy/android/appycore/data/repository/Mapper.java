package com.appy.android.appycore.data.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Mapper<T1, T2> {

    public abstract T2 map(T1 value);

    public abstract T1 reverseMap(T2 value);

    public List<T2> map(List<T1> values) {
        ArrayList<T2> returnValues = new ArrayList<>();
        for (T1 value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }

    public List<T1> reverseMap(List<T2> values) {
        ArrayList<T1> returnValues = new ArrayList<>();
        for (T2 value : values) {
            returnValues.add(reverseMap(value));
        }
        return returnValues;
    }

    public Map<String, T2> map(Map<String, T1> values) {

        Map<String, T2> returnValues = new HashMap<>();
        for (Map.Entry<String, T1> entry : values.entrySet()) {
            returnValues.put(entry.getKey(), map(entry.getValue()));
        }
        return returnValues;
    }

    public Map<String, T1> reverseMap(Map<String, T2> values) {
        Map<String, T1> returnValues = new HashMap<>();
        for (Map.Entry<String, T2> entry : values.entrySet()) {
            returnValues.put(entry.getKey(), reverseMap(entry.getValue()));
        }
        return returnValues;
    }
}