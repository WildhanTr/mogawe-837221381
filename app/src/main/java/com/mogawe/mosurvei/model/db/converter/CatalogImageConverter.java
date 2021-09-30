package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.CatalogImage;

import java.lang.reflect.Type;
import java.util.List;

public class CatalogImageConverter {

    @TypeConverter
    public String restoreList(List<CatalogImage> catalogImages) {
        if (catalogImages == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CatalogImage>>() {}.getType();
        String json = gson.toJson(catalogImages, type);
        return json;
    }

    @TypeConverter
    public List<CatalogImage> saveList(String catalogImagesString) {
        if (catalogImagesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CatalogImage>>() {}.getType();
        List<CatalogImage> catalogImages = gson.fromJson(catalogImagesString, type);
        return catalogImages;
    }

}
