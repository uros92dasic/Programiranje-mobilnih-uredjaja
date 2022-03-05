package com.example.projekat

class ProductLister {
    companion object {
        val products: MutableList<Product> = mutableListOf()

        fun addProducts() {
            products.add(Product("REDDRAGON Slušalice", 5000.00, 12, 3, 6, "Brazil"))
            products.add(Product("SAMSUNG telefon", 66000.00, 10, 2, 4, "Francuska"))
            products.add(Product("ASUS laptop", 123000.00, 5, 4, 8, "Kina"))
            products.add(Product("LG televizor", 40000.00, 7, 3, 6, "Japan"))
            products.add(Product("VOX frižider", 32000.00, 6, 7, 14, "SAD"))
            products.add(Product("MIELE veš mašina", 98000.00, 8, 2, 4, "Nemačka"))
            products.add(Product("BERGEN klima uređaj", 45000.00, 3, 2, 4, "Austrija"))
            products.add(Product("FIAT - Multipla", 300000.00, 2, 15, 30, "Švajcarska"))
        }
    }
}