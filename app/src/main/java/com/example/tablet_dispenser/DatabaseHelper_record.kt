package com.example.tablet_dispenser

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tablet_dispenser.DatabaseHelper.Companion.COL2_RFID

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

    fun registerRecord(rfid:Int,date:String)
    {
        val rfid_string = rfid.toString()
        val new_seq = record_num(rfid)+1
        val db = this.writableDatabase
        val contentValues = ContentValues().apply{
            put(COL1_RFID,rfid_string)
            put(COL2_SEQ,new_seq)
            put(COL3_DATE,date)
        }
        db.insert(TABLE_NAME,null,contentValues)
    }

    fun record_num(rfid:Int) : Int {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT $COL2_SEQ FROM $TABLE_NAME WHERE $COL1_RFID=$rfid ORDER BY $COL2_SEQ DESC",null)
        if(cursor.moveToFirst())
        {
            val seq = cursor.getInt(0)
            cursor.close()
            return seq
        }
        else
        {
            return 0
        }
    }

    fun read_record(rfid:Int) : ArrayList<Pair<String,String>> // record를 저장하는 arraylist
    { // index 0 = seq , index 1 = date
        val db = this.writableDatabase
        var arraylist_record = ArrayList<Pair<String,String>>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL2_RFID = $rfid ORDER BY $COL2_SEQ ASC",null)
        if(cursor.moveToFirst())
        {
            arraylist_record.add(Pair(cursor.getString(1),cursor.getString(2)))
            while(cursor.moveToNext())
            {
                arraylist_record.add(Pair(cursor.getString(1),cursor.getString(2)))

            }
            return arraylist_record
        }
        else
        {
            return arraylist_record
        }
    }
}









