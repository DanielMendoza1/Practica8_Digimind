package daniel.ornelas.digimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import daniel.ornelas.digimind.AdapterRecordatorio
import daniel.ornelas.digimind.R
import daniel.ornelas.digimind.Recordatorio

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var adapter: AdapterRecordatorio? = null
    var recordatorios = ArrayList<Recordatorio>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        cargarRecordatorios()

        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    fun cargarRecordatorios(){
        recordatorios.add(Recordatorio("15", "14:20", "Do Homework"))
        recordatorios.add(Recordatorio("5", "16:00", "Clean the backyard"))
        recordatorios.add(Recordatorio("1", "08:00", "Go to work"))
        recordatorios.add(Recordatorio("11", "18:20", "Do some exercise"))
        recordatorios.add(Recordatorio("15", "14:20", "Do absolutely nothing"))
        recordatorios.add(Recordatorio("22", "17:30", "Take a break"))




    }

}