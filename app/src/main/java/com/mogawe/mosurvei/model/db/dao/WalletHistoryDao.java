package com.mogawe.mosurvei.model.db.dao;

import com.mogawe.mosurvei.model.db.entity.WalletHistory;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WalletHistoryDao {
    @Insert()
    void save(WalletHistory walletHistory);

    @Insert()
    void saveAll(List<WalletHistory> walletHistories);

    @Delete()
    void delete(WalletHistory walletHistory);

    @Query("DELETE FROM walletHistory")
    void deleteAll();

    @Update()
    void update(WalletHistory walletHistory);

    @Query("SELECT * FROM walletHistory LIMIT 1")
    LiveData<WalletHistory> findWalletHistory();

    @Query("SELECT * FROM walletHistory")
    LiveData<List<WalletHistory>> findAll();
}
