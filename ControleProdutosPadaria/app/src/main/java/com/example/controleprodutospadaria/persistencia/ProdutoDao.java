package com.example.controleprodutospadaria.persistencia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.controleprodutospadaria.modelo.Produto;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    long insert(Produto produto);

    @Delete
    int delete(Produto produto);

    @Update
    int update(Produto produto);

    @Query("SELECT * FROM produto WHERE id= :id")
    Produto queryForId(long id);

    @Query("SELECT * FROM produto ORDER BY nomeProduto ASC")
    List<Produto>  queryAllAscending();

    @Query("SELECT * FROM produto ORDER BY nomeProduto DESC")
    List<Produto>  queryAllDownward();

}
