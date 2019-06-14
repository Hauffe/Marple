package com.pucpr.alexandre.marple.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pucpr.alexandre.marple.entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE id IN (:productIds)")
    List<Product> loadAllByIds(int[] productIds);

    @Query("SELECT * FROM product WHERE name LIKE :name LIMIT 1")
    Product findByName(String name);

    @Query("DELETE FROM product")
    int clean();

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);
}
