package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.db.entity.JobTimes;

import java.lang.reflect.Type;
import java.util.List;

public class JobTimeConverter {
    @TypeConverter
    public String fromCountryLangList(List<JobTimes> taskActivities) {
        if (taskActivities == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<JobTimes>>() {}.getType();
        String json = gson.toJson(taskActivities, type);
        return json;
    }

    @TypeConverter
    public List<JobTimes> toCountryLangList(String taskActivitiesString) {
        if (taskActivitiesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<JobTimes>>() {}.getType();
        List<JobTimes> taskActivities = gson.fromJson(taskActivitiesString, type);
        return taskActivities;
    }
}
