package com.example.projekat

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChangeProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_product)

        val index: Int = intent.getIntExtra("index", 0)

        val nameField: EditText = findViewById(R.id.changeName)
        nameField.setText(ProductLister.products.get(index).name)

        val priceField: EditText = findViewById(R.id.changePrice)
        priceField.setText(ProductLister.products.get(index).price.toString())

        val countryField: EditText = findViewById(R.id.changeCountry)
        countryField.setText(ProductLister.products.get(index).country.toString())

        val numProductField: EditText = findViewById(R.id.changeNumProduct)
        numProductField.setText(ProductLister.products.get(index).productCount.toString())

        val standardDeliveryField: EditText = findViewById(R.id.changeStandardDelivery)
        standardDeliveryField.setText(ProductLister.products.get(index).standardDelivery.toString())

        val fastDeliveryField: EditText = findViewById(R.id.changeFastDelivery)
        fastDeliveryField.setText(ProductLister.products.get(index).fastDelivery.toString())

        val esc: Button = findViewById(R.id.escape)
        esc.setOnClickListener {
            //val launchActivity = Intent(this, MainActivity::class.java)
            //startActivity(launchActivity)
            finish()
        }

        val change: Button = findViewById(R.id.change)
        change.setOnClickListener {
            if (nameField.text.toString() == ""
                    || priceField.text.toString() == ""
                    || countryField.text.toString() == ""
                    || numProductField.text.toString() == ""
                    || standardDeliveryField.text.toString() == ""
                    || fastDeliveryField.text.toString() == "")
                Toast.makeText(this, "Niste popunili sva polje!", Toast.LENGTH_LONG).show()

            else {
                ProductLister.products[index] = Product(nameField.text.toString(),
                        priceField.text.toString().toDouble(),
                        numProductField.text.toString().toInt(),
                        fastDeliveryField.text.toString().toInt(),
                        standardDeliveryField.text.toString().toInt(),
                        countryField.text.toString())
                Toast.makeText(this, "Uspešno ste promenili informacije proizvoda.", Toast.LENGTH_LONG).show()
            }
        }
    }
}