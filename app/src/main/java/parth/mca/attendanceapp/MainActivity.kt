package parth.mca.attendanceapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var add : FloatingActionButton
    lateinit var recycleView : RecyclerView
    lateinit var layoutmanager : RecyclerView.LayoutManager
    lateinit var classAdapter: ClassAdapter
    var classitem = ArrayList<ClassItem>()

    lateinit var class_edit : EditText
    lateinit var subject_edit : EditText

    lateinit var cancel : Button
    lateinit var addRec : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add = findViewById(R.id.add)
        recycleView = findViewById(R.id.rv)

        add.setOnClickListener {
            showDailog()
        }

        loadData()

        recycleView.setHasFixedSize(true)

        layoutmanager = LinearLayoutManager(this)

        recycleView.setLayoutManager(layoutmanager)


        classAdapter = ClassAdapter(this,classitem)
        recycleView.adapter = classAdapter
        
    }

    @SuppressLint("Range")
    private fun loadData(){
        var helper = dbHelper(this)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM CLASS_TABLE",null)

        classitem.clear()
        while(rs!!.moveToNext()){
            var id = rs.getInt(rs.getColumnIndex(helper.C_ID))
            var className = rs.getString(rs.getColumnIndex(helper.CLASS_NAME_KEY))
            var subjectName = rs.getString((rs.getColumnIndex(helper.SUBJECT_NAME_KEY)))

            classitem.add(ClassItem(id.toLong(),className,subjectName))
        }

    }


    private fun showDailog() {
        var view = LayoutInflater.from(this).inflate(R.layout.class_dialog,null)

        var builder = AlertDialog.Builder(this)
            builder.setView(view)
        var dailog = builder.create()
            dailog.show()

        class_edit = view.findViewById(R.id.class_edt)
        subject_edit = view.findViewById(R.id.subject_edt)

//      Add & Cancel Button
        cancel = view.findViewById(R.id.cancel)
        addRec = view.findViewById(R.id.add)

        addRec.setOnClickListener {
            addnewclass()
            dailog.dismiss()
        }

        cancel.setOnClickListener {
            dailog.dismiss()
        }

    }

    private fun addnewclass() {
        var cls : String
        var sub : String

        cls = class_edit.text.toString()
        sub = subject_edit.text.toString()

        var cid : Long

        var dbhelper = dbHelper(this)
        cid = dbhelper.addClass(cls,sub)

        var classItem = ClassItem(cid,cls,sub)

        classitem.add(classItem)
        classAdapter.notifyDataSetChanged()


    }
}