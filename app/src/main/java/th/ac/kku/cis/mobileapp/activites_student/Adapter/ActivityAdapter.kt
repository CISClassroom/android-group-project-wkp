package th.ac.kku.cis.mobileapp.activites_student.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_each_list.view.*
import th.ac.kku.cis.mobileapp.activites_student.Model.Activity
import th.ac.kku.cis.mobileapp.activites_student.R

public class ActivityAdapter(var mCtx: Context, var resource:Int, var items:List<Activity>)
    : ArrayAdapter<Activity>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var tvActivityName : TextView = view.findViewById(R.id.txt_activity_name)
        var tvActivityDate : TextView = view.findViewById(R.id.txt_activity_date)


        var activity: Activity = items[position]
        tvActivityName.text = activity.name
        tvActivityDate.text = activity.date

        return view
    }
}
