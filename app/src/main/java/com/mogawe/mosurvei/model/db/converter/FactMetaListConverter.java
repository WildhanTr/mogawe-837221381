package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.FactMeta;
import com.mogawe.mosurvei.model.db.entity.Item;

import java.lang.reflect.Type;
import java.util.List;

public class FactMetaListConverter {
    @TypeConverter
    public String fromCountryLangList(List<FactMeta> items) {
        if (items == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FactMeta>>() {}.getType();
        String json = gson.toJson(items, type);
        return json;
    }

    @TypeConverter
    public List<FactMeta> toCountryLangList(String itemString) {
        if (itemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<FactMeta>>() {}.getType();
        List<FactMeta> items = gson.fromJson(itemString, type);
        return items;
    }
}
