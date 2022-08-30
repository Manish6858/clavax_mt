package com.mahindra.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.newupdate.model.PincodeItemModel

@Dao
interface PincodeDao {
    @Query("SELECT * FROM PincodeItemModel")
    fun getAll(): List<PincodeItemModel>

    @Query("SELECT * FROM PincodeItemModel WHERE id =:userIds")
    fun loadAllByIds(userIds: Int): PincodeItemModel

    @Insert
    fun insertAll(vararg users: PincodeItemModel)

    @Query("DELETE FROM PincodeItemModel")
    fun deleteAll()


    @Query("SELECT * FROM PincodeItemModel WHERE id =:userIds")
    fun exitData(userIds: Int): Boolean
}