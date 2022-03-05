package com.example.projekat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.cart_fragment, container, false)

        val priceField: TextView = view.findViewById(R.id.viewPrice)

        val dateField: TextView = view.findViewById(R.id.viewDate)

        var price = 0.0

        var maxDate = 0

        for (produce in CartLister.cartList) {
            price += produce.price * produce.productCount
            if (produce.standardDelivery >= maxDate) {
                maxDate = produce.standardDelivery
                if (!produce.country.equals(UserLister.userList[0].country))
                    maxDate += 1
            }
        }
        priceField.text = "Cena: ${price}"
        dateField.text = "Broj dana isporuke: ${maxDate}"

        val produceList: ListView = view.findViewById(R.id.produceList)
        produceList.adapter = MyAdapter(view.context)

        produceList.setOnItemClickListener { parent, view, position, id ->
            val launchActivity = Intent(view.context, CancelProduct::class.java)
            launchActivity.putExtra("index", position)
            startActivity(launchActivity)
        }

        val buy: Button = view.findViewById(R.id.confirm)
        buy.setOnClickListener {
            if (CartLister.cartList.size > 0) {
                val launchActivity = Intent(view.context, ConfirmDelivery::class.java)
                startActivity(launchActivity)
            }
        }

        //recreate() pokusaj refreshovanja stranice nakon redukcije/odstranjanja proizvoda
        return view
    }

    companion object {
        fun newInstance(): CartFragment = CartFragment()
    }

    private class MyAdapter(context: Context) : BaseAdapter() {
        private val mContext: Context = context
        override fun getCount(): Int {
            return CartLister.cartList.size
        }

        override fun getItem(position: Int): Any {
            return CartLister.cartList.get(position)
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.setTextSize(20F)
            textView.setText("Naziv proizvoda: " + CartLister.cartList.get(position).name +
                    "\nBroj naručenih proizvoda: " + CartLister.cartList.get(position).productCount +
                    "\nUkupna cena: " + CartLister.cartList.get(position).productCount * CartLister.cartList.get(position).price)

            return textView
        }
    }
}