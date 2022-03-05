package com.example.projekat

class Product(var name: String, var price: Double, var productCount: Int, var fastDelivery: Int, var standardDelivery: Int, var country: String) {

    fun shortPrint(position: Int): String {
        return "Naziv: " + ProductLister.products.get(position).name +
                "\n" + "Cena: " + ProductLister.products.get(position).price
    }
}