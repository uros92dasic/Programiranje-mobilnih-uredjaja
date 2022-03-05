package com.example.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.view_product.*

class ViewProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_product)

        val index: Int = intent.getIntExtra("index", 0)

        val nameField: TextView = findViewById(R.id.name)
        nameField.setText("Naziv proizvoda: " + ProductLister.products.get(index).name)

        val priceField: TextView = findViewById(R.id.price)
        priceField.setText("Cena: " + ProductLister.products.get(index).price.toString())

        val countryField: TextView = findViewById(R.id.country)
        countryField.setText("Zemlja isporuke: " + ProductLister.products.get(index).country)

        val numProductField: TextView = findViewById(R.id.numProduct)
        numProductField.setText("Broj dostupnih proizvoda: " + ProductLister.products.get(index).productCount.toString())

        val choiceNumProductField: EditText = findViewById(R.id.addNumProduct)

        val fastDeliveryField: TextView = findViewById(R.id.fastDelivery)
        fastDeliveryField.setText("Brza isporuka (" + ProductLister.products.get(index).fastDelivery.toString() + " dana): ")

        val standardDeliveryField: TextView = findViewById(R.id.standardDelivery)
        standardDeliveryField.setText("Standardna isporuka (" + ProductLister.products.get(index).standardDelivery.toString() + " dana): ")

        val esc: Button = findViewById(R.id.escape)
        esc.setOnClickListener {
            //val launchActivity = Intent(this, MainActivity::class.java)
            //startActivity(launchActivity)
            finish()
        }

        val cart: Button = findViewById(R.id.addCart)
        cart.setOnClickListener {
            if (addNumProduct.text.toString() == "")
                Toast.makeText(this, "Niste popunili polje za broj proizvoda!", Toast.LENGTH_LONG).show()

            else if (addNumProduct.text.toString().toInt() > ProductLister.products.get(index).productCount)
                Toast.makeText(this, "Izabrali ste više proizvoda nego što ih ima na stanju!", Toast.LENGTH_LONG).show()

            else if (addNumProduct.text.toString().toInt() <= 0)
                Toast.makeText(this, "Izabrali ste 0 proizvoda!", Toast.LENGTH_LONG).show()

            else {
                var helpNum = 0
                for (cartProduce in CartLister.cartList) {
                    if (ProductLister.products.get(index).name.equals(cartProduce.name)) {
                        if ((cartProduce.productCount + choiceNumProductField.text.toString().toInt()) <= ProductLister.products.get(index).productCount) {
                            cartProduce.productCount += choiceNumProductField.text.toString().toInt()
                            Toast.makeText(this, "Uspešno ste dodali proizvod u korpu.", Toast.LENGTH_LONG).show()
                        }

                        else
                            Toast.makeText(this, "Dostupna količina proizvoda se nalazi u vašoj korpi.", Toast.LENGTH_LONG).show()
                        helpNum++
                    }
                }

                if (helpNum == 0) {
                    Toast.makeText(this, "Uspešno ste dodali proizvod u korpu.", Toast.LENGTH_LONG).show()
                    CartLister.cartList.add((Product(ProductLister.products.get(index).name,
                            ProductLister.products.get(index).price,
                            choiceNumProductField.text.toString().toInt(),
                            ProductLister.products.get(index).fastDelivery,
                            ProductLister.products.get(index).standardDelivery,
                            ProductLister.products.get(index).country)))
                }
            }
        }
    }
}