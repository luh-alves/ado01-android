package com.example.adoandroid01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("listaprodutos", Context.MODE_PRIVATE)

        var products = mutableListOf<Product>()

        btnLimpar.setOnClickListener { v: View? ->

            txtNomeProdutos.text.clear()
            txtCustoProdutos.text.clear()
            txtVendaProdutos.text.clear()
            txtLucroProdutos.text.clear()
        }
//        fun calcularLucro() {
//            var valorTotal =
//        }

        btnSalvar.setOnClickListener { v: View? ->
            if (txtNomeProdutos.text.isNotEmpty()) {
                products.add(
                    Product(
                        txtNomeProdutos.text.toString(),
                        txtCustoProdutos.text.toString().toFloat(),
                        txtVendaProdutos.text.toString().toFloat(),
                        txtLucroProdutos.text.toString().toFloat()
                    )
                )

                val encoded = Json.encodeToString(products)

                sharedPreferences.edit().putString("produtos", encoded).apply()
                Toast.makeText(this, "Produto Salvo", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nome do produto inexistente", Toast.LENGTH_SHORT).show()
            }
        }
        btnAbrir.setOnClickListener { v: View? ->
            if (txtNomeProdutos.text.isNotEmpty()) {
                var texto = sharedPreferences.getString("produtos", "[]")
                products = Json.decodeFromString<MutableList<Product>>(texto.toString())

                val results = products.filter { product -> product.nome ==  txtNomeProdutos.text.toString()}

                if (results.isEmpty()) {
                    Toast.makeText(this, "Produto Vazio", Toast.LENGTH_SHORT).show()
                } else {
                    val produtoCarregado = results[0]
                    txtNomeProdutos.setText(produtoCarregado.nome)
                    txtCustoProdutos.setText(produtoCarregado.precoCusto.toString())
                    txtVendaProdutos.setText(produtoCarregado.precoVenda.toString())
                    txtLucroProdutos.setText(produtoCarregado.lucro.toString())


                    Toast.makeText(this, "Produto carregado com sucesso", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Nome do produto inexistente", Toast.LENGTH_SHORT).show()
            }
        }

    }
}