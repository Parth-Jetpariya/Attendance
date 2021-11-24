package parth.mca.attendanceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var helper = dbHelper(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM STUDENT_TABLE",null)

        if(rs.moveToFirst()){
            Toast.makeText(this,rs.getString(1)+rs.getString(2)+rs.getString(3),Toast.LENGTH_LONG).show()
            rs.moveToNext()
            Toast.makeText(this,rs.getString(1)+rs.getString(2)+rs.getString(3),Toast.LENGTH_LONG).show()

//            Toast.makeText(this,"Y-2",Toast.LENGTH_LONG).show()

        }
        else Toast.makeText(this,"Null",Toast.LENGTH_LONG).show()
    }
}