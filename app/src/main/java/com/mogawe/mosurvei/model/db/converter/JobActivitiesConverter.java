package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.bean.Task.TaskActivities;

import java.lang.reflect.Type;
import java.util.List;

public class JobActivitiesConverter {
    @TypeConverter
    public String restoreList(List<TaskActivities> taskActivities) {
        if (taskActivities == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TaskActivities>>() {}.getType();
        String json = gson.toJson(taskActivities, type);
        return json;
    }

    @TypeConverter
    public List<TaskActivities> saveList(String taskActivitiesString) {
        if (taskActivitiesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TaskActivities>>() {}.getType();
        List<TaskActivities> taskActivities = gson.fromJson(taskActivitiesString, type);
        return taskActivities;
    }
}
