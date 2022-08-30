package com.newupdate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PincodeItemModel(@PrimaryKey(autoGenerate = false) val id: Int,
                            @ColumnInfo(name = "zipcode") val zipcodes: Int,

                            )
