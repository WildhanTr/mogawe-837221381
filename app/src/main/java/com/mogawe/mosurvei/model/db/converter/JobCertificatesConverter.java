package com.mogawe.mosurvei.model.db.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.bean.Certificate;
import com.mogawe.mosurvei.model.bean.Task.TaskActivities;

import java.lang.reflect.Type;
import java.util.List;

public class JobCertificatesConverter {
    @TypeConverter
    public String restoreList(List<Certificate> certificateList) {
        if (certificateList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Certificate>>() {}.getType();
        String json = gson.toJson(certificateList, type);
        return json;
    }

    @TypeConverter
    public List<Certificate> saveList(String certificatesString) {
        if (certificatesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Certificate>>() {}.getType();
        List<Certificate> certificates = gson.fromJson(certificatesString, type);
        return certificates;
    }
}
