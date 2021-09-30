package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mogawe.mosurvei.model.db.entity.Notification;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.util.List;

@Dao
public interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Notification notification);

    @Delete()
    void delete(Notification notification);

    @Query("DELETE FROM notification")
    void deleteAll();

    @Query("SELECT * FROM notification")
    LiveData<List<Notification>> findAll();
}
