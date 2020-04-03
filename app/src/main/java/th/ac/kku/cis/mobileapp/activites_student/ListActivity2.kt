package th.ac.kku.cis.mobileapp.activites_student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list2.*
import th.ac.kku.cis.mobileapp.activites_student.Model.Activity2
import th.ac.kku.cis.mobileapp.activites_student.Adapter.ActivityAdapter2


class ListActivity2 : AppCompatActivity() {
    val TAG = "List2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)

        imageView5.setOnClickListener {
            val i = Intent(this,AddActivity::class.java)
            startActivity(i)
        }

        Firebase.database.reference.child("activities").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                val listView: ListView = findViewById(R.id.listActivity2)
                val activities_list = mutableListOf<Activity2>()

                for (postSnapshot in p0.children) {

                    Log.d(TAG,postSnapshot.key.toString())
                    Log.d(TAG,postSnapshot.child("name").toString())
                    Log.d(TAG,postSnapshot.child("date").toString())
                    activities_list.add(Activity2(postSnapshot.child("name").getValue().toString(),postSnapshot.key.toString(),postSnapshot.child("date").getValue().toString()))
                }

                listView.adapter = ActivityAdapter2(
                    this@ListActivity2,
                    R.layout.activity_each_list_2,
                    activities_list
                )
                /*listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as Activity2
                    //Toast.makeText(this@ListActivity, selectedItem.nickname, Toast.LENGTH_SHORT).show()
                    val i = Intent(this@ListActivity2,activityAttendees::class.java)
                    i.putExtra("activityNameS",selectedItem.nickname)
                    i.putExtra("activityNameL",selectedItem.name)
                    startActivity(i)
                }*/
            }
        })



    }
}
