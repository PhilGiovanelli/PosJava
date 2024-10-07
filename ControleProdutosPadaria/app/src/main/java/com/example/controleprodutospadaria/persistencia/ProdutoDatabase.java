package com.example.controleprodutospadaria.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.controleprodutospadaria.modelo.Produto;

@Database(entities = {Produto.class}, version=1, exportSchema = false)
public abstract class ProdutoDatabase extends RoomDatabase {

   public abstract ProdutoDao getProdutoDao();

   private static ProdutoDatabase instance;

   public static ProdutoDatabase getDatabase(final Context context){

       if(instance==null){
           synchronized (ProdutoDatabase.class){
               if (instance==null){
                   instance = Room.databaseBuilder(context,ProdutoDatabase.class,"produto.db").allowMainThreadQueries().build();
               }
           }
       }

       return  instance;
   }

}
