package parth.mca.attendanceapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbHelper(context: Context) : SQLiteOpenHelper(context,"STUDENT",null,1) {

    // class table
    var CLASS_TABLE_NAME = "CLASS_TABLE"
    var C_ID = "_CID"
    var CLASS_NAME_KEY = "CLASS_NAME"
    var SUBJECT_NAME_KEY = "SUBJECT_NAME"
    var DROP_CLASS_TABLE = "DROP TABLE IF EXISTS"+CLASS_TABLE_NAME

    //student table
    var STUDENT_TABEL_NAME = "STUDENT_TABLE"
    var S_ID = "_SID"
    var CLASS_ID = "C_ID"
    var ROLL = "ROLL"
    var STUDENT_NAME = "STUDENT_NAME"

    //status table
    var STATUS_TABLE_NAME = "STATUS_TABLE"
    var STATUS_ID = "_ID"
    var STUDENT_ID = "S_ID"
    var DATE = "DATE"
    var STATUS = "STATUS"





    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE "+CLASS_TABLE_NAME+" ("+C_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CLASS_NAME_KEY+
                " TEXT NOT NULL,"+SUBJECT_NAME_KEY+" TEXT NOT NULL,UNIQUE("+CLASS_NAME_KEY+","+SUBJECT_NAME_KEY+"))")

        db?.execSQL("CREATE TABLE "+STUDENT_TABEL_NAME+"("+S_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CLASS_ID+" INTEGER,"+
                ROLL+" INTEGER NOT NULL, "+STUDENT_NAME+" TEXT NOT NULL)")

//        db?.execSQL("CREATE TABLE "+STATUS_TABLE_NAME+" ("+STATUS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+STUDENT_ID+
//                " TEXT NOT NULL,"+DATE+" TEXT NOT NULL,"+STATUS+" TEXT NOT NULL)")

        db?.execSQL("CREATE TABLE STATUS_TABLE (_ID INTEGER PRIMARY KEY AUTOINCREMENT,S_ID TEXT NOT NULL,DATE TEXT NOT NULL,STATUS TEXT NOT NULL)")

//        db?.execSQL("INSERT INTO STATUS_TABLE (S_ID,DATE,STATUS) VALUES (1,1,'P')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
//            db?.execSQL(DROP_CLASS_TABLE)

    }

   fun addClass(classname : String,subjectname : String): Long {
       var db = writableDatabase
       var cv = ContentValues()
       cv.put(CLASS_NAME_KEY,classname)
       cv.put(SUBJECT_NAME_KEY,subjectname)

       return db.insert(CLASS_TABLE_NAME,null,cv)
   }

    fun addstudent(cid : Int,roll : Int,name : String): Long {
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(CLASS_ID,cid)
        cv.put(ROLL,roll)
        cv.put(STUDENT_NAME,name)

        return db.insert(STUDENT_TABEL_NAME,null,cv)
    }

    fun addstatus(sid: Int, date: String, status: String): Long {

        var db = writableDatabase
        var cv = ContentValues()
        cv.put(STUDENT_ID,sid)
        cv.put(DATE,date)
        cv.put(STATUS,status)

        return db.insert(STATUS_TABLE_NAME,null,cv)
    }

}