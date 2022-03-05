package com.example.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import android.content.DialogInterface
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    val mOnNavigationItemSelectedLister =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.review -> {
                        toolbar.title = "Prikaz proizvoda"
                        openFragment(ViewProductsFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.addProduct -> {
                        toolbar.title = "Dodaj proizvod"
                        openFragment(AddProductFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.shoppingCart -> {
                        toolbar.title = "Korpa proizvoda"
                        openFragment(CartFragment.newInstance())
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.logout -> {
                        toolbar.title = "Odjavi se"
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage("Odjavi se?")
                            .setCancelable(false)
                            .setPositiveButton(
                                "Da",
                                DialogInterface.OnClickListener { dialog, id ->
                                    this.finish()
                                })
                            .setNegativeButton("Ne", DialogInterface.OnClickListener { dialog, id ->
                                dialog.cancel()
                            })
                        val alert = builder.create()
                        alert.show()
                    }
                }

                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        toolbar.title = "Aplikacija za kupovinu proizvoda!"

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedLister)

        if (ProductLister.products.isEmpty()) {
            ProductLister.addProducts()
        }

        if (UserLister.userList.size == 0) {
            val launchActivity = Intent(this, AddUser::class.java)
            startActivity(launchActivity)
        }

        openFragment(ViewProductsFragment.newInstance())
    }
}