package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.Item;

import java.lang.reflect.Type;
import java.util.List;

public class ItemListConverter {
    @TypeConverter
    public String fromCountryLangList(List<Item> items) {
        if (items == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<List<Item>>() {}.getType();
        String json = gson.toJson(items, type);
        return json;
    }

    @TypeConverter
    public List<Item> toCountryLangList(String itemString) {
        if (itemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Item>>() {}.getType();
        List<Item> items = gson.fromJson(itemString, type);
        return items;
    }
}
