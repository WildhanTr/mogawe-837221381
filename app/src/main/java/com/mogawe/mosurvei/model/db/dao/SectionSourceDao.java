package com.mogawe.mosurvei.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;

import java.util.List;

@Dao
public interface SectionSourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(SectionSourceResponse sectionSourceResponse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<SectionSourceResponse> sectionSourceResponses);

    @Delete()
    void delete(SectionSourceResponse sectionSourceResponse);

    @Query("DELETE FROM sectionsourceresponse WHERE idJob = :jobId AND uuidSession = :uuidSession")
    void deleteByUserId(int jobId, String uuidSession);

    @Query("DELETE FROM sectionsourceresponse WHERE id_task = :id_task")
    void deleteByIdTask(String id_task);


    @Query("DELETE FROM sectionsourceresponse")
    void deleteAll();

    @Update()
    void update(SectionSourceResponse sectionSourceResponse);

    /**
     * Updating row sectionSource data
     * By order id
     */
    @Query("UPDATE sectionsourceresponse SET sections = :sections WHERE idJob = :idJob AND uuidSession = :uuidSession")
    void updateById(List<Section> sections, int idJob, String uuidSession);

    @Query("UPDATE sectionsourceresponse SET sections = :sections WHERE id_task = :idTask")
    void updateByIdTask(List<Section> sections, String idTask);

    @Query("SELECT * FROM sectionsourceresponse LIMIT 1")
    LiveData<SectionSourceResponse> findSectionSource();

    @Query("SELECT * FROM sectionsourceresponse")
    LiveData<List<SectionSourceResponse>> findAll();

    @Query("SELECT * FROM sectionsourceresponse WHERE idJob = :idJob AND isSubmitted = :isSubmitted LIMIT 1")
    LiveData<SectionSourceResponse> findSectionByIdJob(int idJob, Boolean isSubmitted);

    @Query("SELECT * FROM sectionsourceresponse WHERE id = :id")
    LiveData<SectionSourceResponse> findSectionById(int id);

    @Query("SELECT * FROM sectionsourceresponse WHERE uuidSession = :uuidSession")
    LiveData<SectionSourceResponse> findSectionByUuidSession(String uuidSession);

    @Query("SELECT * FROM sectionsourceresponse WHERE id_task = :id_task AND idJob = :id_job AND isSubmitted = :isSubmitted LIMIT 1")
    LiveData<SectionSourceResponse> findSectionByIdTask(String id_task, Integer id_job, Boolean isSubmitted);
}
