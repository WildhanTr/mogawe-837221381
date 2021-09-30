package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.bean.Task.TaskActivities;
import com.mogawe.mosurvei.model.db.entity.CommissionsItem;

import java.lang.reflect.Type;
import java.util.List;

public class CommissionsItemConverter {

    @TypeConverter
    public String restoreList(List<CommissionsItem> commissionsItems) {
        if (commissionsItems == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CommissionsItem>>() {}.getType();
        String json = gson.toJson(commissionsItems, type);
        return json;
    }

    @TypeConverter
    public List<CommissionsItem> saveList(String commissionsItemsString) {
        if (commissionsItemsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<CommissionsItem>>() {}.getType();
        List<CommissionsItem> commissionsItems = gson.fromJson(commissionsItemsString, type);
        return commissionsItems;
    }

}
