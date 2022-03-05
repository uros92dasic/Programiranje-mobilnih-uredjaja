package com.example.projekat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.view_product.*

class CancelProduct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cancel_product_cart)
        val index: Int = intent.getIntExtra("index", 0)

        val nameField: TextView = findViewById(R.id.name)
        nameField.setText("Naziv proizvoda: " + CartLister.cartList.get(index).name)

        val priceField: TextView = findViewById(R.id.price)
        priceField.setText("Cena: " + CartLister.cartList.get(index).price.toString())

        val countryField: TextView = findViewById(R.id.country)
        countryField.setText("Zemlja isporuke: " + CartLister.cartList.get(index).country)

        val numProductField: TextView = findViewById(R.id.numProduct)
        numProductField.setText("Broj dostupnih proizvoda: " + CartLister.cartList.get(index).productCount.toString())

        val choiceNumProductField: EditText = findViewById(R.id.addNumProduct)
        choiceNumProductField.setText(CartLister.cartList.get(index).productCount.toString())

        val fastDeliveryField: TextView = findViewById(R.id.fastDelivery)
        fastDeliveryField.setText("Brza isporuka (" + CartLister.cartList.get(index).fastDelivery.toString() + " dana).")

        val standardDeliveryField: TextView = findViewById(R.id.standardDelivery)
        standardDeliveryField.setText("Standardna isporuka (" + CartLister.cartList.get(index).standardDelivery.toString() + " dana).")

        //val i = Intent(this, CartFragment::class.java)
        //pokusaj "refreshovanja" stranice CartFragment, uz startActiviry(i) dole, nakon izmene broja ili uklanjanja proizvoda iz korpe

        val esc: Button = findViewById(R.id.escape)
        esc.setOnClickListener {
            //val launchActivity = Intent(this, MainActivity::class.java)
            //startActivity(launchActivity)
            finish()
        }

        val cart: Button = findViewById(R.id.cancelCart)
        cart.setOnClickListener {
            if (addNumProduct.text.toString() == "") {
                Toast.makeText(this, "Niste popunili polje za broj proizvoda!", Toast.LENGTH_LONG)
                    .show()
            }

            else if (addNumProduct.text.toString().toInt() > CartLister.cartList.get(index).productCount) {
                Toast.makeText(this, "Izabrali ste više proizvoda nego što ih ima u korpi!", Toast.LENGTH_LONG).show()
            }

            else if (addNumProduct.text.toString().toInt() < CartLister.cartList.get(index).productCount) {
                CartLister.cartList.get(index).productCount = (CartLister.cartList.get(index).productCount) - (addNumProduct.text.toString().toInt())
                Toast.makeText(this, "Smanjili ste broj izabranog proizvoda.", Toast.LENGTH_LONG).show()
                //val launchActivity = Intent(this, MainActivity::class.java)
                //startActivity(launchActivity)

                //startActivity(i)
                finish()
            }

            else if (addNumProduct.text.toString().toInt() <= CartLister.cartList.get(index).productCount) {
                CartLister.cartList.removeAt(index)
                Toast.makeText(this, "Izbrisali ste proizvod iz korpe.", Toast.LENGTH_LONG).show()
                //val launchActivity = Intent(this, MainActivity::class.java)
                //startActivity(launchActivity)

                //startActivity(i)
                finish()   /*  komentar>>>  */
            }
        }
    }
}
