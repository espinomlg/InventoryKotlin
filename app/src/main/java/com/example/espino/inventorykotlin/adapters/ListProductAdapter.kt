package com.example.espino.inventorykotlin.adapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.espino.inventorykotlin.R
import com.example.espino.inventorykotlin.models.Product


/**
 * Created by espino on 24/05/17.
 */
class ListProductAdapter(context: Context) : CursorAdapter(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER){


    override fun newView(context: Context?, p1: Cursor?, parent: ViewGroup?): View {
        var v: View = LayoutInflater.from(context).inflate(R.layout.listitem_product, parent, false)
        var holder = ProductHolder()

        holder.serial = v.findViewById(R.id.productitem_serial) as TextView
        holder.shortname = v.findViewById(R.id.productitem_shortname) as TextView
        holder.category = v.findViewById(R.id.productitem_category) as TextView
        holder.subcategory = v.findViewById(R.id.productitem_subcategory) as TextView

        v.tag = holder

        return v
    }

    override fun bindView(view: View?, p1: Context?, p2: Cursor?) {
        var holder = view?.tag as ProductHolder

        holder.serial.text = cursor.getString(1)
        holder.shortname.text = cursor.getString(2)
        holder.category.text = cursor.getString(4)
        holder.category.text = cursor.getString(5)
    }

    override fun getItem(position: Int): Product {
        cursor.moveToPosition(position)

        var product = Product(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4).toString(),
                cursor.getInt(5).toString(),
                cursor.getInt(6).toString())

        return product
    }

    class ProductHolder{
        lateinit var serial: TextView
        lateinit var shortname: TextView
        lateinit var category: TextView
        lateinit var subcategory: TextView

    }

}