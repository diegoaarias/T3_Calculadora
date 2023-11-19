package com.example.t3_calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.t3_calculadora.databinding.ActivityMainBinding
import com.example.t3_calculadora.model.Calculadora

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var number1: Int = 0
    private var number2: Int = 0
    private var operador: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        acciones()
    }

    private fun instancias() {
    }

    private fun acciones() {
        binding.boton0.setOnClickListener(this)
        binding.boton1.setOnClickListener(this)
        binding.boton2.setOnClickListener(this)
        binding.boton3.setOnClickListener(this)
        binding.boton4.setOnClickListener(this)
        binding.boton5.setOnClickListener(this)
        binding.boton6.setOnClickListener(this)
        binding.boton7.setOnClickListener(this)
        binding.boton8.setOnClickListener(this)
        binding.boton9.setOnClickListener(this)
        binding.botonAC.setOnClickListener(this)
        binding.botonCambio.setOnClickListener(this)
        binding.botonPorcentaje.setOnClickListener(this)
        binding.botonDivision.setOnClickListener(this)
        binding.botonMultiplicar.setOnClickListener(this)
        binding.botonResta.setOnClickListener(this)
        binding.botonSumar.setOnClickListener(this)
        binding.botonIgual.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.botonAC -> {
                binding.textView.text = ""
                number1 = 0
                number2 = 0
                operador = null
            }
            R.id.botonCambio -> {
                val numero = binding.textView.text.toString().toIntOrNull() ?: return
                binding.textView.text = (numero * -1).toString()
            }
            R.id.botonPorcentaje -> {
                val numero = binding.textView.text.toString().toIntOrNull() ?: return
                binding.textView.text = (numero / 100).toString()
            }

            R.id.boton0 -> appendNumero(0)
            R.id.boton1 -> appendNumero(1)
            R.id.boton2 -> appendNumero(2)
            R.id.boton3 -> appendNumero(3)
            R.id.boton4 -> appendNumero(4)
            R.id.boton5 -> appendNumero(5)
            R.id.boton6 -> appendNumero(6)
            R.id.boton7 -> appendNumero(7)
            R.id.boton8 -> appendNumero(8)
            R.id.boton9 -> appendNumero(9)

            R.id.botonSumar -> operadorSeleccionado('+')
            R.id.botonResta -> operadorSeleccionado('-')
            R.id.botonMultiplicar -> operadorSeleccionado('*')
            R.id.botonDivision -> operadorSeleccionado('/')

            R.id.botonIgual -> calcular()
        }
    }

    private fun appendNumero(num: Int) {
        binding.textView.text = "${binding.textView.text}$num"
    }

    private fun operadorSeleccionado(op: Char) {
        number1 = binding.textView.text.toString().toIntOrNull() ?: return
        operador = op
        binding.textView.text = ""
    }

    private fun calcular() {
        number2 = binding.textView.text.toString().toIntOrNull() ?: return
        operador?.let {
            val result = Calculadora.calculate(number1, number2, it)
            binding.textView.text = result.toString()
            number1 = result
        }
    }
}
