package th.ac.kku.cis.mobileapp.activites_student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_regis.setOnClickListener{
            val i = Intent(this,Regis::class.java)
            startActivity(i)
        }
        btn_manage.setOnClickListener{
            val i = Intent(this,ListActivity2::class.java)
            startActivity(i)
}

        btn_check.setOnClickListener{
            val i = Intent(this,ListActivity::class.java)
            startActivity(i)
}

}
}
