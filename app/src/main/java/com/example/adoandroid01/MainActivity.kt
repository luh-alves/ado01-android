package com.example.adoandroid01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("listaprodutos", Context.MODE_PRIVATE)

        val products = mutableListOf<Product>()

        btnLimpar.setOnClickListener { v: View? ->
            txtListaProdutos.text.clear()
            txtNomeProdutos.text.clear()
            txtCustoProdutos.text.clear()
            txtVendaProdutos.text.clear()
            txtLucroProdutos.text.clear()
        }
        fun calcularLucro() {

        }

        btnSalvar.setOnClickListener { v: View? ->
            if (txtNomeProdutos.text.isNotEmpty()) {
                products.add(
                    Product(
                        txtNomeProdutos.text.toString(),
                        txtCustoProdutos.text.toString().toFloat(),
                        txtVendaProdutos.text.toString().toFloat()
                    )
                )

                sharedPreferences.edit().putStringSet("produtos", products.toSet())
                Toast.makeText(this, "Produto Salvo", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nome do produto inexistente", Toast.LENGTH_SHORT).show()
            }
        }
        btnAbrir.setOnClickListener { v: View? ->
            if (txtNomeProdutos.text.isNotEmpty()) {
                var texto = lp.getString(txtNomeProdutos.text.toString(), "")
                if (texto.isNullOrEmpty()) {
                    Toast.makeText(this, "Produto Vazio", Toast.LENGTH_SHORT).show()
                } else {
                    txtListaProdutos.setText(texto)
                    Toast.makeText(this, "Produto carregado com sucesso", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Nome do produto inexistente", Toast.LENGTH_SHORT).show()
            }
        }

    }
}