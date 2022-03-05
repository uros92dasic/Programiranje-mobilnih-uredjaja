package com.example.projekat

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ConfirmDelivery : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm_cart)
        val index: Int = intent.getIntExtra("index", 0)

        val changeDeliveryAddress: EditText = findViewById(R.id.changeDeliveryAddress)

        val fastDeliveryField: RadioButton = findViewById(R.id.rbFast)

        val slowDeliveryField: RadioButton = findViewById(R.id.rbSlow)

        val confirm: Button = findViewById(R.id.confirmDelivery)
        confirm.setOnClickListener {
            var address = UserLister.userList[0].address
            var date = 0
            var maxDate = 0
            var minDate: Int = CartLister.cartList.get(index).fastDelivery
            var price = 0.0

            for (produce in CartLister.cartList) {
                price += produce.price * produce.productCount
                if (produce.standardDelivery >= maxDate) {
                    maxDate = produce.standardDelivery
                    if (!produce.country.equals(UserLister.userList[0].country)) {
                        maxDate += 1
                    }
                }

                if (produce.fastDelivery <= minDate) {
                    minDate = produce.fastDelivery
                    if (!produce.country.equals(UserLister.userList.get(0).country)) {
                        minDate += 1
                    }
                }
            }

            if (!changeDeliveryAddress.text.toString().equals(""))
                address = changeDeliveryAddress.text.toString()

            if (fastDeliveryField.isChecked) {
                price *= 1.2
                date = minDate
            }

            else if (slowDeliveryField.isChecked) {
                price *= 0.9
                date = maxDate + 2
            }

            else
                date = maxDate

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Adresa isporuke: " + address
                    + "\nBroj dana isporuke: " + date
                    + "\nCena porudžbine: " + price)
                    .setCancelable(false)
                    .setPositiveButton(
                            "Potvrdi",
                            DialogInterface.OnClickListener { dialog, id ->
                                for (produce in ProductLister.products) {
                                    for (cartProduce in CartLister.cartList) {
                                        if (produce.name.equals(cartProduce.name)) {
                                            produce.productCount -= cartProduce.productCount
                                        }
                                    }
                                }
                                CartLister.cartList = mutableListOf()
                                this.finish()
                            })
                    .setNegativeButton("Otkaži", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })
            val alert = builder.create()
            alert.show()
        }

        val esc: Button = findViewById(R.id.escape)
        esc.setOnClickListener {
            //val launchActivity = Intent(this, MainActivity::class.java)
            //startActivity(launchActivity)
            finish()
        }
    }
}