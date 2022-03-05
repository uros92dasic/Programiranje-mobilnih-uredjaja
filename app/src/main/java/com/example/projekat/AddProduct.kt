package com.example.projekat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)

        val nameField: EditText = findViewById(R.id.addName)

        val priceField: EditText = findViewById(R.id.addPrice)

        val countryField: EditText = findViewById(R.id.addCountry)

        val numProductField: EditText = findViewById(R.id.addNumProduct)

        val standardDeliveryField: EditText = findViewById(R.id.addStandardDelivery)

        val fastDeliveryField: EditText = findViewById(R.id.addFastDelivery)

        val esc: Button = findViewById(R.id.escape)
        esc.setOnClickListener {
            //val launchActivity = Intent(this, MainActivity::class.java)
            //startActivity(launchActivity)
            finish()
        //sa ovim ne vracamo svaki put na MainActivity, da se ne bi svaki put vracali u tab preko navigacije ukoliko nismo zavrsili dodavanje
        //ukoliko je pozeljno da se svaki put vrati u main activity, u komentar staviti finish() a val launchActivity i startActivity aktivirati
        //tako i u svakom sledecoj metodi gde ima isto
        }

        val add: Button = findViewById(R.id.add)
        add.setOnClickListener {
            if (nameField.text.toString() == ""
                    || priceField.text.toString() == ""
                    || countryField.text.toString() == ""
                    || numProductField.text.toString() == ""
                    || standardDeliveryField.text.toString() == ""
                    || fastDeliveryField.text.toString() == "")
                Toast.makeText(this, "Niste popunili sva polje!", Toast.LENGTH_LONG).show()

            else {
                ProductLister.products.add(Product(nameField.text.toString(),
                        priceField.text.toString().toDouble(),
                        numProductField.text.toString().toInt(),
                        fastDeliveryField.text.toString().toInt(),
                        standardDeliveryField.text.toString().toInt(),
                        countryField.text.toString()))
                Toast.makeText(this, "Uspešno ste dodali proizvod.", Toast.LENGTH_LONG).show()
                nameField.setText("")
                priceField.setText("")
                countryField.setText("")
                numProductField.setText("")
                standardDeliveryField.setText("")
                fastDeliveryField.setText("")
            }
        }
    }
}