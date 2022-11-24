package com.example.tablet_dispenser

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import android.widget.Toast
import java.nio.IntBuffer

class DatabaseHelper private constructor(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION)
{
    companion object{
        const val DATABASE_NAME = "pillData.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "pill"
        const val COL1_ID = "_id"
        const val COL2_RFID = "rfid"
        const val COL3_NAME = "name"
        const val COL4_BIRTHDAY = "birthday"
        const val COL5_P1 = "p1"
        const val COL6_P2 = "p2"
        const val COL7_P3 = "p3"

        @Volatile
        private var instance : DatabaseHelper? = null

        fun getInstance(context: Context) =
            instance?: synchronized(DatabaseHelper::class.java){
                instance ?:DatabaseHelper(context).also {
                    instance = it
                }
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE $TABLE_NAME ("+
                "$COL1_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "$COL2_RFID INTEGER, " +
                "$COL3_NAME TEXT, "+
                "$COL4_BIRTHDAY TEXT, "+
                "$COL5_P1 INTEGER, "+
                "$COL6_P2 INTEGER, "+
                "$COL7_P3 INTEGER"+
                ")"
        db?.execSQL(createQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion!=newVersion)
        {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
    fun RegisterData(rfid:Int,name:String,birthday:String)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply{
            put(COL2_RFID,rfid)
            put(COL3_NAME,name)
            put(COL4_BIRTHDAY,birthday)
            put(COL5_P1,0)
            put(COL6_P2,0)
            put(COL7_P3,0)
        }
        db.insert(TABLE_NAME,null,contentValues)
    }

    fun insertUserData(rfid:Int,name:String,birthday:String)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply{
            put(COL2_RFID,rfid)
            put(COL3_NAME,name)
            put(COL4_BIRTHDAY,birthday)
        }
        db.insert(TABLE_NAME,null,contentValues)
    }

    fun insertPillData(rfid:Int,p1:String,p2:String,p3:String)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply{
            put(COL2_RFID,rfid)
            put(COL5_P1,p1)
            put(COL6_P2,p2)
            put(COL7_P3,p3)
        }
        db.update(TABLE_NAME,contentValues,"$COL1_ID = ?",arrayOf(rfid.toString()))
    }

    fun readUserData(rfid:Int): Pair<String,String>{ // id를 받고 textview에 이름과 나이를 반환
        val db = this.writableDatabase
        val cursor= db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL2_RFID=$rfid",null)
        cursor.moveToFirst()
        val name = cursor.getString(2)
        val birthday = cursor.getString(3)
        cursor.close()
        return Pair<String,String>(name,birthday)
    }

    fun readPillData(rfid:Int) : Triple<Int,Int,Int>{ // id를 받고 textview에 알약 개수를 반환
        val db = this.writableDatabase
        val cursor= db.rawQuery("SELECT FROM $TABLE_NAME WHERE $COL2_RFID=$rfid",null)
        val p1 = cursor.getInt(3)
        val p2 = cursor.getInt(4)
        val p3 = cursor.getInt(5)
        cursor.close()
        return Triple(p1,p2,p3) // 리턴값 알약 개수 세 개
    }

    fun checkDataExists(rfid:Int) : Boolean{
        val db =this.writableDatabase
        var cursor = db.rawQuery("SELECT $COL2_RFID FROM $TABLE_NAME ",null)
        if(cursor.moveToFirst()) { //값이 있다면
            while(true) {
                if (cursor.getInt(0).equals(rfid)) // 값이 rfid와 같다면
                {
                    return false
                    break
                }
                if (!cursor.moveToNext()) // 다음 열이 없다면
                {
                    return true
                    break
                }
            }

        }
        return true
    }
}