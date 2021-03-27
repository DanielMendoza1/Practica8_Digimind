package daniel.ornelas.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import daniel.ornelas.digimind.R
import daniel.ornelas.digimind.ui.Task

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var adaptador: AdaptadorTareas? = null


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
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        if (first){
            fillTask()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)

        val gridView: GridView = root.findViewById(R.id.gridView)

        gridView.adapter = adaptador

        return root
    }

  fun fillTask(){

      tasks.add(Task("practica 1", arrayListOf("Tuesday","Sunday"), "17:30"))
      tasks.add(Task("practica 2", arrayListOf("Friday","Sunday"), "19:30"))
      tasks.add(Task("practica 3", arrayListOf("Thursday","Sunday"), "05:10"))
      tasks.add(Task("practica 4", arrayListOf("Sunday","Sunday"), "04:14"))
      tasks.add(Task("practica 5", arrayListOf("Saturday","Sunday"), "06:45"))
      tasks.add(Task("practica 6", arrayListOf("Monday","Sunday"), "12:12"))
      tasks.add(Task("practica 7", arrayListOf("Sunday","Friday"), "01:10"))


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