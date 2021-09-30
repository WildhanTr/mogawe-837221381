package com.mogawe.mosurvei.model.db.dao;

import com.mogawe.mosurvei.model.db.entity.Result;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Result result);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Result> results);

    @Delete()
    void delete(Result result);

    @Query("DELETE FROM result")
    void deleteAll();

    @Update()
    void update(Result result);

    @Query("SELECT * FROM result LIMIT 1")
    LiveData<Result> findResult();

    @Query("SELECT * FROM result")
    LiveData<List<Result>> findAll();
}
