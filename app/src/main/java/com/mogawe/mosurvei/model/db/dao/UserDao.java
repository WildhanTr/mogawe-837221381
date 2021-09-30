package com.mogawe.mosurvei.model.db.dao;


import com.mogawe.mosurvei.model.db.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert()
    void save(User user);

    @Insert()
    void saveAll(List<User> users);

    @Delete()
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Update()
    void update(User user);

    @Query("SELECT * FROM user LIMIT 1")
    LiveData<User> findUser();
}

