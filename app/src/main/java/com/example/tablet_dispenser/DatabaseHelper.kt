package com.example.tablet_dispenser

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import java.nio.IntBuffer

class DatabaseHelper private constructor(context: Context) :SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION)
{
    companion object{
        const val DATABASE_NAME = "pillData.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "pill"
        const val COL1_ID = "_id"
        const val COL2_NAME = "name"
        const val COL3_BIRTHDAY = "birthday"
        const val COL4_P1 = "p1"
        const val COL5_P2 = "p2"
        const val COL6_P3 = "p3"

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
                "$COL1_ID INTEGER, "+
                "$COL2_NAME TEXT, "+
                "$COL3_BIRTHDAY INTEGER, "+
                "$COL4_P1 INTEGER, "+
                "$COL5_P2 INTEGER, "+
                "$COL6_P3 INTEGER "+
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

    fun insertUserData(id:String,name:String,birthday:String)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply{
            put(COL1_ID,id)
            put(COL2_NAME,name)
            put(COL3_BIRTHDAY,birthday)
        }
        db.insert(TABLE_NAME,null,contentValues)
    }

    fun insertPillData(id:String,p1:String,p2:String,p3:String)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply{
            put(COL1_ID,id)
            put(COL4_P1,p1)
            put(COL5_P2,p2)
            put(COL6_P3,p3)
        }
        db.update(TABLE_NAME,contentValues,"$COL1_ID = ?",arrayOf(id))
    }

    fun readUserData(id:String): Pair<String,String>{ // id를 받고 textview에 이름과 나이를 반환
        var result : String
        val db = this.writableDatabase
        val stringBuffer = StringBuffer()
        val cursor= db.rawQuery("SELECT FROM $TABLE_NAME WHERE ID=$id",null)
        val name = cursor.getString(1)
        val birthday = cursor.getString(2)
        result = stringBuffer.toString()
        cursor.close()
        return Pair<String,String>(name,birthday)
    }

    fun readPillData(id:String) : Triple<Int,Int,Int>{ // id를 받고 textview에 알약 개수를 반환
        val db = this.writableDatabase
        val cursor= db.rawQuery("SELECT FROM $TABLE_NAME WHERE ID=$id",null)
        val p1 = cursor.getInt(3)
        val p2 = cursor.getInt(4)
        val p3 = cursor.getInt(5)
        cursor.close()
        return Triple(p1,p2,p3) // 리턴값 알약 개수 세 개
    }
}