package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class FileListConverter {
    @TypeConverter
    public String fromCountryLangList(List<File> files) {
        if (files == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<List<File>>() {}.getType();
        String json = gson.toJson(files, type);
        return json;
    }

    @TypeConverter
    public List<File> toCountryLangList(String fileString) {
        if (fileString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<File>>() {}.getType();
        List<File> files = gson.fromJson(fileString, type);
        return files;
    }
}
