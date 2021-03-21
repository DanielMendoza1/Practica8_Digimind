package daniel.ornelas.digimind

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterRecordatorio: BaseAdapter {

    var recordatorios = ArrayList<Recordatorio>()
    var context: Context?  = null

    constructor(context: Context, recordatorios: ArrayList<Recordatorio>?) {
        this.context = context
        if (recordatorios != null) {
            this.recordatorios = recordatorios
        }


    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var recordatorio = recordatorios[position]
        var inflator = LayoutInflater.from(context)
        var vista = inflator.inflate(R.layout.recordatorio, null)

        var nombre = vista.findViewById(R.id.txtNombreRecordatorio) as TextView
        var dias = vista.findViewById(R.id.txtDiasRecordatorio) as TextView
        var tiempo = vista.findViewById(R.id.txtTiempoRecordatorio) as TextView

        nombre.setText(recordatorio.nombre)
        dias.setText(recordatorio.dias)
        tiempo.setText(recordatorio.tiempo)

        return vista

    }

    override fun getItem(position: Int): Any {
        return recordatorios[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recordatorios.size
    }
}