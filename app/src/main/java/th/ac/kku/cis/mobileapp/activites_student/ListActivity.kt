package th.ac.kku.cis.mobileapp.activites_student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import th.ac.kku.cis.mobileapp.activites_student.Model.Activity
import th.ac.kku.cis.mobileapp.activites_student.Adapter.ActivityAdapter

class ListActivity : AppCompatActivity() {
        val TAG = "List"
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        Firebase.database.reference.child("activities").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val listView: ListView = findViewById(R.id.listActivity)
                val activities_list = mutableListOf<Activity>()

                for (postSnapshot in p0.children) {

                    Log.d(TAG,postSnapshot.key.toString())
                    Log.d(TAG,postSnapshot.child("name").toString())
                    Log.d(TAG,postSnapshot.child("date").toString())
                activities_list.add(Activity(postSnapshot.child("name").getValue().toString(),postSnapshot.key.toString(),postSnapshot.child("date").getValue().toString()))
                }

                listView.adapter = ActivityAdapter(
                    this@ListActivity,
                    R.layout.activity_each_list,
                    activities_list
                )
                listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as Activity
                    //Toast.makeText(this@ListActivity, selectedItem.nickname, Toast.LENGTH_SHORT).show()
                    val i = Intent(this@ListActivity,activityAttendees::class.java)
                    i.putExtra("activityNameS",selectedItem.nickname)
                    i.putExtra("activityNameL",selectedItem.name)
                    startActivity(i)
                }
            }
        })
        }
}
