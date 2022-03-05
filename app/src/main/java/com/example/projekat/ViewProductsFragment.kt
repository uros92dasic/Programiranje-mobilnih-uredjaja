package com.example.projekat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.product_view_fragment.view.*

class ViewProductsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.product_view_fragment, container, false)

        val productList: ListView = view.product_list
        productList.adapter = MyAdapter(view.context)

        productList.setOnItemClickListener { parent, view, position, id ->
            val launchActivity = Intent(view.context, ViewProduct::class.java)
            launchActivity.putExtra("index", position)
            startActivity(launchActivity)
        }

        return view
    }

    companion object {
        fun newInstance(): ViewProductsFragment = ViewProductsFragment()
    }

    private class MyAdapter(context: Context) : BaseAdapter() {
        var products = ProductLister.products

        private val mContext: Context = context
        override fun getCount(): Int {
            return products.size
        }

        override fun getItem(position: Int): Any {
            return products.get(position)
        }

        override fun getItemId(position: Int): Long {
            val long: Long = 0
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.setTextSize(20F)
            textView.text = ProductLister.products.get(position).shortPrint(position)

            return textView
        }
    }
}