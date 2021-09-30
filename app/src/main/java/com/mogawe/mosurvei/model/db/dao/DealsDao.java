package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.db.entity.Result;

import java.util.List;

@Dao
public interface DealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Deals deals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Deals> deals);

    @Delete()
    void delete(Deals deals);

    @Query("DELETE FROM Deals")
    void deleteAll();

    @Update()
    void update(Deals deals);

    @Query("SELECT * FROM Deals LIMIT 1")
    LiveData<Deals> findResult();

    @Query("SELECT * FROM Deals")
    LiveData<List<Deals>> findAll();
}
