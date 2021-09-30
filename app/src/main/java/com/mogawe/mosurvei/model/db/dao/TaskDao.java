package com.mogawe.mosurvei.model.db.dao;

import com.mogawe.mosurvei.model.db.entity.Task;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Task task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Task> task);

    @Delete()
    void delete(Task task);

    @Query("DELETE FROM task")
    void deleteAll();

    @Update()
    void update(Task task);

    @Query("SELECT * FROM task LIMIT 1")
    LiveData<Task> findTask();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> findAll();
}
