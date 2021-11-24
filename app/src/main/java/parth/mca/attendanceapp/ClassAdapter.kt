package parth.mca.attendanceapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class ClassAdapter: RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    var classitem = ArrayList<ClassItem>()
    var context :Context


    constructor(context: MainActivity, classItem: ArrayList<ClassItem>){
        this.classitem = classItem
        this.context = context
    }

    open class ClassViewHolder:RecyclerView.ViewHolder {

        var claass : TextView
        var subject : TextView

        constructor(itemView: View) : super(itemView) {
            claass = itemView.findViewById(R.id.class_tv)
            subject = itemView.findViewById(R.id.subject_tv)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.class_item,parent,false)
        return ClassViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.claass.setText(classitem.get(position).Class)
        holder.subject.setText(classitem.get(position).Subject)

        holder.claass.setOnClickListener {

            val intent = Intent(context,student_Activity::class.java)
            intent.putExtra("classname",classitem.get(position).Class)
            intent.putExtra("subjectname",classitem.get(position).Subject)
            intent.putExtra("position",position)
            intent.putExtra("cid",classitem.get(position).id.toInt())

            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return classitem.size
    }

}