package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mogawe.mosurvei.model.db.entity.PendingTask;
import com.mogawe.mosurvei.model.db.entity.Task;

import java.util.List;

@Dao
public interface TaskPendingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(PendingTask pendingTask);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<PendingTask> pendingTasks);

    @Delete()
    void delete(PendingTask pendingTask);

    @Query("DELETE FROM pendingtask")
    void deleteAll();

    @Query("DELETE FROM pendingtask WHERE jobId = :idJob AND uuidResult = :uuidResult")
    void deleteByUserId(int idJob, String uuidResult);

    @Update()
    void update(PendingTask pendingTask);

    @Query("SELECT * FROM pendingtask LIMIT 1")
    LiveData<PendingTask> findTask();

    @Query("SELECT * FROM pendingtask")
    LiveData<List<PendingTask>> findAll();
}
