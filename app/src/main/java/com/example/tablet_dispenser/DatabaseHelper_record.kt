package com.example.tablet_dispenser

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper_record private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION)
{
    companion object{
        const val DATABASE_NAME="record.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "record"
        const val COL1_RFID = "rfid"
        const val COL2_SEQ = "seq"
        const val COL3_DATE = "date"


        @Volatile
        private var instance : DatabaseHelper_record? = null

        fun getInstance(context: Context) =
            instance?: synchronized(DatabaseHelper_record::class.java){
                instance ?:DatabaseHelper_record(context).also {
                    instance = it
                }
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE $TABLE_NAME ("+
                "$COL1_RFID TEXT, "+
                "$COL2_SEQ INT, "+
                "$COL3_DATE TEXT"+
                ")"
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion!=newVersion)
        {
            db?.execSQL("DROP TABLE IF EXISTS ${DatabaseHelper_record.TABLE_NAME}")
            onCreate(db)
        }
    }
}









