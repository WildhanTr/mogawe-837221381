package com.mogawe.mosurvei.model.db.dao;


import com.mogawe.mosurvei.model.db.entity.Tracking;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TrackingDao {

    @Insert()
    void save(Tracking tracking);

    @Insert()
    void saveAll(List<Tracking> trackings);

    @Delete()
    void delete(Tracking tracking);

    @Query("DELETE FROM tracking")
    void deleteAll();

    @Update()
    void update(Tracking tracking);

    @Query("SELECT * FROM tracking LIMIT 1")
    LiveData<Tracking> findTracking();

    @Query("SELECT * FROM tracking")
    LiveData<List<Tracking>> findAll();
}

