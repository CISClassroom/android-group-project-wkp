package th.ac.kku.cis.mobileapp.activites_student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class activityAttendees : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendees)

        val i = intent
        val ActnameS = intent.getStringExtra("activityNameS")
        val ActnameL = intent.getStringExtra("activityNameL")


        Firebase.database.reference.child("activityAttendees").child(ActnameS).addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(p0: DataSnapshot) {

                val TvName = findViewById<TextView>(R.id.Actname)
                TvName.text = ActnameL

                val arrayAdapter: ArrayAdapter<*>
                val studentlist = ArrayList<String>()

                for (std in p0.children){
                    studentlist.add(std.key+" "+std.getValue().toString())
                }


                var mListView = findViewById<ListView>(R.id.eachStudentList)
                arrayAdapter = ArrayAdapter(this@activityAttendees, android.R.layout.simple_list_item_1, studentlist)
                mListView.adapter = arrayAdapter

                mListView.setOnItemClickListener { parent, view, position, id ->

                    val selectedItem = parent.getItemAtPosition(position) as String
                    Toast.makeText(this@activityAttendees,selectedItem, Toast.LENGTH_SHORT).show()

                }





            }
        })

        }
}
