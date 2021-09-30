package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.Fact;

import java.lang.reflect.Type;
import java.util.List;

public class FactListConverter {
    @TypeConverter
    public String fromCountryLangList(List<Fact> facts) {
        if (facts == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Fact>>() {}.getType();
        String json = gson.toJson(facts, type);
        return json;
    }

    @TypeConverter
    public List<Fact> toCountryLangList(String factString) {
        if (factString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Fact>>() {}.getType();
        List<Fact> facts = gson.fromJson(factString, type);
        return facts;
    }
}
