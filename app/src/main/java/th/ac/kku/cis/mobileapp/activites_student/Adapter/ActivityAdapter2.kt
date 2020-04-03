package th.ac.kku.cis.mobileapp.activites_student.Adapter


import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_each_list.view.*
import th.ac.kku.cis.mobileapp.activites_student.Model.Activity2
import th.ac.kku.cis.mobileapp.activites_student.R

public class ActivityAdapter2(var mCtx: Context, var resource:Int, var items:List<Activity2>)
    : ArrayAdapter<Activity2>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var tvActivityName : TextView = view.findViewById(R.id.txt_activity_name_2)
        var tvActivityDate : TextView = view.findViewById(R.id.txt_activity_date_2)


        var activity: Activity2 = items[position]
        tvActivityName.text = activity.name
        tvActivityDate.text = activity.date

        var btnDel : TextView = view.findViewById(R.id.btn_del)


        btnDel.setOnClickListener {

            val id = activity.nickname
            val myDatabase = FirebaseDatabase.getInstance().getReference("activities")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("ยืนยันการลบ?")
            builder.setMessage("ยืนยันการลบกิจกรรม "+id)
            builder.setPositiveButton("ยืนยัน"){dialog, which ->
                myDatabase.child(id).removeValue().addOnSuccessListener {
                    Toast.makeText(context,"ลบกิจกรรม "+id+" เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
                }

            }
            builder.setNegativeButton("กลับ"){dialog,which ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return view
    }
}
