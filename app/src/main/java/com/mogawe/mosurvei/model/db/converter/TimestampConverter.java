package com.mogawe.mosurvei.model.db.converter;

import com.mogawe.mosurvei.util.ui.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.room.TypeConverter;

public class TimestampConverter {

    private static DateFormat df = new SimpleDateFormat(DateUtil.FULL_FORMAT, new Locale("id", "ID"));

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String toTimestamp(Date value) {
        if (value != null) {
            try {
                return df.format(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        } else {
            return "";
        }
    }

}
