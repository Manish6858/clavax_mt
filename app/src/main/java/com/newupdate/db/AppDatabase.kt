package com.mahindra.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.newupdate.model.PincodeItemModel


@Database(entities = [PincodeItemModel::class], version = 2)
abstract class AppDatabase() : RoomDatabase() {
    private var INSTANCE: AppDatabase? = null
    abstract fun pincodeDao(): PincodeDao



    open fun getAppDatabase(context: Context): AppDatabase? {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase::class.java, "zipcodes"
            ).allowMainThreadQueries()
                .build()

            //Room.inMemoryDatabaseBuilder(context.getApplicationContext(),AppDatabase.class).allowMainThreadQueries().build();
        }
        return INSTANCE
    }

    open fun destroyInstance() {
        INSTANCE = null
    }

}