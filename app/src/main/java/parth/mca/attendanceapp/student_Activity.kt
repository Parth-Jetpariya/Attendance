package parth.mca.attendanceapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList
import parth.mca.attendanceapp.dbHelper as dbHelper1

class student_Activity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var adapter : StudentAdapter
    lateinit var layoutmanager : RecyclerView.LayoutManager
    var studentItem = ArrayList<StudentItem>()

    lateinit var classname : String
    lateinit var subjectname : String
    var position : Int = 0
    var cid : Int = 0

    lateinit var toolbar : Toolbar

    lateinit var roll_edit : EditText
    lateinit var name_edit : EditText

    lateinit var subtitle : TextView

    var calender = Calendar.getInstance()
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)

    lateinit var date : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

//        this.supportActionBar?.hide()

        classname = intent.getStringExtra("classname").toString()
        subjectname = intent.getStringExtra("subjectname").toString()
        position = intent.getIntExtra("position",-1)
        cid = intent.getIntExtra("cid",-2)

        date = "$day/${month+1}/$year"

        loadData()

        recyclerView = findViewById(R.id.student_recycleView)
        recyclerView.setHasFixedSize(true)
        layoutmanager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(layoutmanager)
        adapter = StudentAdapter(this,studentItem)
        recyclerView.adapter = adapter
        loadStatusData()

        setToolBar()

        registerForContextMenu(recyclerView)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.student_context_menu,menu)

        menu?.setHeaderTitle("Context Menu")

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.update -> {
                Toast.makeText(this,"Click On Update",Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this,"Click On Delete",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    @SuppressLint("Range")
    private fun loadData(){
        var helper = dbHelper1(this)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM STUDENT_TABLE WHERE C_ID=?", arrayOf(cid.toString()))


        studentItem.clear()
        while(rs!!.moveToNext()){
            var sid = rs.getInt(rs.getColumnIndex(helper.S_ID))
            var c_id = rs.getString(rs.getColumnIndex(helper.CLASS_ID))
            var roll = rs.getString(rs.getColumnIndex(helper.ROLL))
            var name = rs.getString((rs.getColumnIndex(helper.STUDENT_NAME)))

            studentItem.add(StudentItem(sid.toLong(),roll,name))
        }
        rs.close()
    }

    private fun setToolBar() {
        toolbar = findViewById(R.id.toolbar)
        var title = findViewById<TextView>(R.id.title_toolbar)
        subtitle = findViewById(R.id.subtitle_toolbar)
        var back =  findViewById<ImageButton>(R.id.back)
        var save =  findViewById<ImageButton>(R.id.save)

        title.setText(classname)
        subtitle.setText(subjectname+" | "+date)

        back.setOnClickListener{
            super.onBackPressed()
        }

        save.setOnClickListener {
            var pos = 0
            for(item in studentItem){
                var status : String
                status =  studentItem.get(pos).Status
                var value :Long = dbHelper1(this).addstatus(studentItem.get(pos).sid.toInt(),date,status)
                if (value.toInt() == -1){
                    Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show()

                }

                Toast.makeText(this,"Data Insert",Toast.LENGTH_SHORT).show()
                pos++
            }
        }

    }

    @SuppressLint("Range")
    private fun loadStatusData() {
        var helper = dbHelper1(this)
        var db = helper.readableDatabase
        var pos = 0

        for (item in studentItem){
            var status = ""
            var rs = db.rawQuery("SELECT * FROM STATUS_TABLE WHERE DATE=? AND S_ID=?", arrayOf(date,studentItem.get(pos).sid.toString()))
            if(rs.moveToFirst())
                status = rs.getString(3)
            studentItem.get(pos).Status = status
            pos++
        }
        adapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.student_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_student -> {
                showDialog()
                true
            }
            R.id.add_calander ->{
                DatePickerDialog(this,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    subtitle.setText(subjectname+" | $i3/${i2+1}/$i")
                    date = "$i3/${i2+1}/$i"
                    loadStatusData()

                },year,month,day).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showDialog() {
        var view = LayoutInflater.from(this).inflate(R.layout.student_dialog,null)

        var builder = AlertDialog.Builder(this).setView(view)

        var dailog = builder.create()
        dailog.show()


        roll_edit = view.findViewById(R.id.roll_edt)
        
        name_edit = view.findViewById(R.id.name_edt)
        var add = view.findViewById<Button>(R.id.add)
        var cancel = view.findViewById<Button>(R.id.cancel)

        add.setOnClickListener {
            addStudent()
            dailog.dismiss()
        }

        cancel.setOnClickListener {
            dailog.dismiss()
        }

    }

    private fun addStudent() {
        var roll : String
        var name : String

        roll = roll_edit.text.toString()
        name = name_edit.text.toString()

        var helper = dbHelper1(this)
        var sid = helper.addstudent(cid,roll.toInt(),name)


        var studentItems = StudentItem(sid,roll,name)
        studentItem.add(studentItems)
        adapter.notifyDataSetChanged()

    }


}


