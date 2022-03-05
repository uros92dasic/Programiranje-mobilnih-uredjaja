package com.example.projekat

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user)

        val name: EditText = findViewById(R.id.name)

        val surname: EditText = findViewById(R.id.surname)

        val email: EditText = findViewById(R.id.email)

        val address: EditText = findViewById(R.id.address)

        val country: EditText = findViewById(R.id.country)

        val confirm: Button = findViewById(R.id.confirm)

        confirm.setOnClickListener {
            if (!name.text.toString().equals("")
                    || !surname.text.toString().equals("")
                    || !email.text.toString().equals("")
                    || !address.text.toString().equals("")
                    || !country.text.toString().equals("")) {
                if (isValidEmail(email.text.toString())) {
                    var user = User(name.text.toString(), surname.text.toString(), email.text.toString(), address.text.toString(), country.text.toString())
                    Toast.makeText(this, "Uspešno ste se registrovali.", Toast.LENGTH_LONG).show()
                    UserLister.userList.add(user)
                    name.setText("")
                    surname.setText("")
                    email.setText("")
                    address.setText("")
                    country.setText("")
                    val launchActivity = Intent(this, MainActivity::class.java)
                    startActivity(launchActivity)

                } else
                    Toast.makeText(this, "Email adresa nije ispravna!", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, "Niste popunili sva polja!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}