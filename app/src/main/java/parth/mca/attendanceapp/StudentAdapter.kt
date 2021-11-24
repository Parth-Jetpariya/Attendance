package parth.mca.attendanceapp

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class StudentAdapter: RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    var studentitem = ArrayList<StudentItem>()
    var context : Context


    constructor(context: Context,studentItem: ArrayList<StudentItem>){
        this.studentitem = studentItem
        this.context = context

    }


    open class StudentViewHolder:RecyclerView.ViewHolder {

        var roll : TextView
        var name : TextView
        var status : TextView
        var cardview : CardView

        constructor(itemView: View) : super(itemView) {
            roll = itemView.findViewById(R.id.roll)
            name = itemView.findViewById(R.id.name)
            status = itemView.findViewById(R.id.status)
            cardview = itemView.findViewById(R.id.cv)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false)
        return StudentViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.roll.setText(studentitem.get(position).Roll)
        holder.name.setText(studentitem.get(position).Name)
        holder.status.setText(studentitem.get(position).Status)



        var status = studentitem.get(position).Status
        if(status.equals("P")) {
            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.present))
        }
        else if(status.equals("A")) {
            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.absent))
        }
        else
            holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))

        holder.name.setOnClickListener {
            if(status.equals("P")) {
                status = "A"
                holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.absent))
                studentitem.get(position).Status = status

            }
            else {
                status = "P"
                holder.cardview.setCardBackgroundColor(ContextCompat.getColor(context, R.color.present))
                studentitem.get(position).Status = status
            }
            holder.status.setText(status)
        }

        holder.cardview.setOnClickListener {
            Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return studentitem.size
    }

}



