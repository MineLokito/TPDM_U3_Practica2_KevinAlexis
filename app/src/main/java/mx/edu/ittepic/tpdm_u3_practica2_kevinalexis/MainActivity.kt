package mx.edu.ittepic.tpdm_u3_practica2_kevinalexis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject
import java.net.URL
import java.util.ArrayList

class MainActivity:AppCompatActivity() {
    var descripcionn: EditText? = null
    var montoo: EditText? = null
    var RSI_Pago: RadioButton? = null
    var RNO_Pago: RadioButton? = null
    var insertar: Button? = null
    var cargar: Button? = null
    var mostrar: Button? = null
    var etiqueta: TextView? = null
    var fechaa: EditText? = null
    var jsonRespuesta = ArrayList<JSONObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        descripcionn = findViewById(R.id.Descripcion)
        montoo = findViewById(R.id.Monto)
        fechaa = findViewById(R.id.Fecha)
        RSI_Pago = findViewById(R.id.SiPago)
        RNO_Pago = findViewById(R.id.NoPago)
        insertar = findViewById(R.id.insertar)
        cargar = findViewById(R.id.cargar)
        mostrar = findViewById(R.id.mostrar)
        etiqueta = findViewById(R.id.etiqueta)

        insertar?.setOnClickListener {
            var conexionWeb = ConexionWeb(this)
            conexionWeb.agregarVariables("descripcion", descripcionn?.text.toString()+"\n")
            conexionWeb.agregarVariables("monto", montoo?.text.toString()+"\n")
            conexionWeb.agregarVariables("fechavencimiento", fechaa?.text.toString()+"\n")
            conexionWeb.agregarVariables("pagado", RSI_Pago?.isChecked.toString()+"\n")
            conexionWeb.execute(URL("https://shrouded-savannah-21293.herokuapp.com/insertar.php"))



        }
        cargar?.setOnClickListener {
            var conexionWeb= ConexionWeb(this)
            conexionWeb.execute(URL("https://shrouded-savannah-21293.herokuapp.com/consultagenerica.php"))


        }
        mostrar?.setOnClickListener {

            val posicion=descripcionn?.text.toString().toInt()
            val jsonObject= jsonRespuesta.get(posicion)
            etiqueta?.setText("Descripcion: "+jsonObject.getString("descripcion"))
        }

    }
    fun mostrarResultados(result: String) {

        var alerta = AlertDialog.Builder(this)
        alerta.setTitle("Respuesta del Servidor").setMessage(result)
            .setPositiveButton("ok") { dialogInterface, wich -> }
            .show()
        val jsonarray=org.json.JSONArray(result)
        var total=jsonarray.length()-1
        (0..total).forEach {
            jsonRespuesta.add(jsonarray.getJSONObject(it))
        }

        etiqueta?.setText(result)

    }

}
