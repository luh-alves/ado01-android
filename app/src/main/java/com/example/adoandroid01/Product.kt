package com.example.adoandroid01

import kotlinx.serialization.Serializable

@Serializable
class Product(
    val nome: String,
    val precoCusto: Float,
    val precoVenda: Float
);