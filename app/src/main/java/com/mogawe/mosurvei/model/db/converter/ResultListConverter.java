package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.Result;

import java.lang.reflect.Type;
import java.util.List;

public class ResultListConverter {
    @TypeConverter
    public String fromCountryLangList(List<Result> results) {
        if (results == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Result>>() {}.getType();
        String json = gson.toJson(results, type);
        return json;
    }

    @TypeConverter
    public List<Result> toCountryLangList(String resultString) {
        if (resultString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Result>>() {}.getType();
        List<Result> results = gson.fromJson(resultString, type);
        return results;
    }
}
