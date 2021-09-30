package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.Section;

import java.lang.reflect.Type;
import java.util.List;

public class SectionListConverter {
    @TypeConverter
    public String fromCountryLangList(List<Section> sections) {
        if (sections == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Section>>() {}.getType();
        String json = gson.toJson(sections, type);
        return json;
    }

    @TypeConverter
    public List<Section> toCountryLangList(String sectionString) {
        if (sectionString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Section>>() {}.getType();
        List<Section> sections = gson.fromJson(sectionString, type);
        return sections;
    }
}
