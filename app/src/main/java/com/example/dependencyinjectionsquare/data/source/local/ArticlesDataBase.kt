package com.example.dependencyinjectionsquare.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dependencyinjectionsquare.data.source.local.dao.ArticleDao
import com.example.dependencyinjectionsquare.data.source.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class] , version = 1 )
abstract class ArticlesDataBase : RoomDatabase() {


    abstract fun getArticleDao()  :ArticleDao

    companion object{

       private var instance : ArticlesDataBase? = null

        fun init(context : Context) : ArticlesDataBase {
            val temp = instance

            if ( temp != null){
                   return temp
            }
              val Instance =  Room.databaseBuilder(context , ArticlesDataBase::class.java,"data.db")
                  .allowMainThreadQueries()
                  .build()

            instance = Instance
            return instance as ArticlesDataBase

        }
    }
}