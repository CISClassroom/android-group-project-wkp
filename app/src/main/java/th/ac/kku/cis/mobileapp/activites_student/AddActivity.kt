package th.ac.kku.cis.mobileapp.activites_student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*
import th.ac.kku.cis.mobileapp.activites_student.Model.addActivity

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val TAG= "AddActivity"
        var SelectDate = ""

        val calendarView = findViewById<CalendarView>(R.id.calendarActivity)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            SelectDate = dayOfMonth.toString() + "/" + (month + 1) + "/" + (year+543)
            Log.d(TAG,"Select Date : "+SelectDate)
            //Toast.makeText(this, SelectDate, Toast.LENGTH_SHORT).show()
        }

        btn_confirm_add.setOnClickListener {
            Toast.makeText(this,SelectDate,Toast.LENGTH_LONG).show()
            val myDatabase = FirebaseDatabase.getInstance().getReference("activities")
            val nameS= tb_getnameS.text.toString()
            val nameL= tb_getnameL.text.toString()
            val date = SelectDate

            if(nameS==""||nameL==""||date==""){
                Toast.makeText(this, "กรอกข้อมูลไม่ครบถ้วน", Toast.LENGTH_SHORT).show()
            }else{
                val activity = addActivity(nameL,date)
                myDatabase.child(nameS).setValue(activity).addOnCompleteListener{
                    Toast.makeText(this,"เพิ่มสำเร็จ",Toast.LENGTH_SHORT).show()
                    var i = Intent(this,MainActivity::class.java)
                    startActivity(i)
                }
            }


        }

    }
}
