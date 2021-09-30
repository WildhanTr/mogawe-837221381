package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mogawe.mosurvei.model.db.entity.Catalog;

import java.util.List;

@Dao
public interface CatalogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Catalog catalog);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<Catalog> catalogs);

    @Delete()
    void delete(Catalog catalog);

    @Query("DELETE FROM Catalog")
    void deleteAll();

    @Update()
    void update(Catalog catalog);

    @Query("SELECT * FROM Catalog LIMIT 1")
    LiveData<Catalog> findResult();

    @Query("SELECT * FROM Catalog")
    LiveData<List<Catalog>> findAll();

}
