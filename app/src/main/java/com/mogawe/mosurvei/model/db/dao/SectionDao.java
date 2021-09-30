package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mogawe.mosurvei.model.db.entity.Section;

import java.util.List;

@Dao
public interface SectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Section section);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Section> sections);

    @Delete()
    void delete(Section section);

    @Query("DELETE FROM section")
    void deleteAll();

    @Update()
    void update(Section section);

    @Query("SELECT * FROM section LIMIT 1")
    LiveData<Section> findSection();

    @Query("SELECT * FROM section")
    LiveData<List<Section>> findAll();
}
