package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mogawe.mosurvei.model.db.entity.ResultList;

import java.util.List;

@Dao
public interface ResultListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(ResultList result);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<ResultList> results);

    @Query("SELECT * FROM resultlist")
    LiveData<List<ResultList>> findAll();

    @Query("DELETE FROM resultlist WHERE idJob = :jobId AND uuidResult = :uuidResult")
    void deleteByIdJobAndUuidSession(int jobId, String uuidResult);

    @Query("SELECT * FROM resultlist WHERE idJob = :jobId AND uuidResult = :uuidResult")
    LiveData<ResultList> findResultByIdJobAndUuidSession(int jobId, String uuidResult);

}
