package th.ac.kku.cis.mobileapp.activites_student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_regis.*
import java.time.LocalDate

class Regis : AppCompatActivity() {
    val TAG = "RegisPage"
    var idStudent :String? = ""
    var nameStudent :String? = ""
    var selectActivity :String? = ""
    var AllActivity = arrayOf("==เลือกกิจกรรม==")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)

        Firebase.database.reference.child("activities").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                        AllActivity += postSnapshot.key.toString()
//                        Log.d(TAG,"========>"+postSnapshot.key.toString())
                    }

                    spinner.adapter = ArrayAdapter(this@Regis,android.R.layout.simple_spinner_item,AllActivity)

                    spinner.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            var activity = AllActivity[position]
                            if(activity=="==เลือกกิจกรรม=="){
                                selectActivity=""
                            }else{
                                selectActivity=activity
                            }
                            Log.d(TAG,"Activity Select = "+selectActivity.toString())
                        }
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            selectActivity=""
                        }

                    }

                    btn_confirm.setOnClickListener {

                        //======================================================================================
                        Firebase.database.reference.child("students").child(tb_getid.text.toString()).addValueEventListener(object : ValueEventListener {
                            override fun onCancelled(p0: DatabaseError) {}
                            override fun onDataChange(p0: DataSnapshot) {
                                idStudent = tb_getid.text.toString()
                                nameStudent = p0.child("name").getValue().toString()

                                if(nameStudent=="null"){
                                    Toast.makeText(this@Regis,"ไม่พบรหัสนักศึกษาในระบบ",Toast.LENGTH_SHORT).show()
                                }

                                else if(idStudent==""||selectActivity==""||nameStudent==""){
                                    Toast.makeText(this@Regis,"ข้อมูลไม่ครบถ้วน",Toast.LENGTH_SHORT).show()
                                }

                                else{
                                    Toast.makeText(this@Regis,"ข้อมูลครบถ้วน",Toast.LENGTH_SHORT).show()
                                    AddtoFirebase(idStudent!!,nameStudent!!,selectActivity!!)
                                }

                            }
                        })


                        //=======================================================================================

                        /*if(idStudent==""||selectActivity==""||nameStudent==""){
                            Toast.makeText(this@Regis,"ข้อมูลไม่ครบถ้วน",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this@Regis,"ข้อมูลครบถ้วน",Toast.LENGTH_SHORT).show()
                            AddtoFirebase(idStudent!!,nameStudent!!,selectActivity!!)
                            //AddtoFirebase(subId!!,subName!!,selectRoom!!,selectDay!!,selectMonth!!,selectYear!!,selectTime!!,subSeatstart!!)
                        }*/

                    }
                }


            override fun onCancelled(p0: DatabaseError) {}
            })

    }

    private fun AddtoFirebase(idStudent:String,nameStudent:String,selectActivity:String){

        val idStudent = idStudent
        val nameStudent= nameStudent
        val nameActivity= selectActivity
        val myDatabase = FirebaseDatabase.getInstance().getReference("activityAttendees")

        //val subject = SubjectAddFirebase(name,room,date,time,seatstart)
        myDatabase.child(nameActivity).child(idStudent).setValue(nameStudent).addOnCompleteListener{
            Toast.makeText(this,"เพิ่มสำเร็จ",Toast.LENGTH_SHORT).show()
            val intent = intent
            finish()
            startActivity(intent)
        }


    }

}
