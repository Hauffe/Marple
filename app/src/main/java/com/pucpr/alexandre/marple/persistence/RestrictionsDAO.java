package com.pucpr.alexandre.marple.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pucpr.alexandre.marple.entity.Restriction;

import java.util.List;

@Dao
public interface RestrictionsDAO {

    @Query("SELECT * FROM restriction")
    List<Restriction> getAll();

    @Query("SELECT * FROM restriction WHERE id IN (:restrictionIds)")
    List<Restriction> loadAllByIds(int[] restrictionIds);

    @Query("SELECT * FROM restriction WHERE name LIKE :name LIMIT 1")
    Restriction findByName(String name);

    @Query("DELETE FROM restriction")
    int clean();

    @Insert
    void insertAll(Restriction... restrictions);

    @Delete
    void delete(Restriction restrictions);
}
