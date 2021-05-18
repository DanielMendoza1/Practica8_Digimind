package daniel.ornelas.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import daniel.ornelas.digimind.R
import daniel.ornelas.digimind.ui.Task

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var adaptador: AdaptadorTareas? = null

    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth


    companion object {
        var tasks = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        tasks = ArrayList()
        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()


        fillTask()

        if (!tasks.isEmpty()){
            var gridView: GridView = root.findViewById(R.id.gridView)

            adaptador = AdaptadorTareas(root.context, tasks)
            gridView.adapter = adaptador
        }

        return root
    }

  fun fillTask(){

       storage.collection("actividades")
           .whereEqualTo("email", usuario.currentUser.email)
           .get()
           .addOnSuccessListener {
              it.forEach {
                  var dias = ArrayList<String>()
                  if (it.getBoolean("lu") == true){
                      dias.add("Monday")
                  }
                  if (it.getBoolean("ma") == true){
                      dias.add("Tuesday")
                  }
                  if (it.getBoolean("mi") == true){
                      dias.add("Wednesday")
                  }
                  if (it.getBoolean("ju") == true){
                      dias.add("Thursday")
                  }
                  if (it.getBoolean("vi") == true){
                      dias.add("Friday")
                  }
                  if (it.getBoolean("sa") == true){
                      dias.add("Saturday")
                  }
                  if (it.getBoolean("do") == true){
                      dias.add("Sunday")
                  }
                  tasks!!.add(Task(it.getString("actividad")!!, dias, it.getString("tiempo")!!))
              }

           }
           .addOnFailureListener {

               Toast.makeText(context, "Error: intente nuevamente", Toast.LENGTH_SHORT).show()
           }

  }

    private class AdaptadorTareas:BaseAdapter{
        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto:Context, tasks: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView = vista.findViewById(R.id.tv_title)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)
            var tv_days: TextView = vista.findViewById(R.id.tv_days)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista

        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }
    }

}