package com.example.espino.inventorykotlin.fragments

import android.app.Activity
import android.app.AlertDialog
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ListFragment
import android.view.*
import android.widget.AdapterView
import com.example.espino.inventorykotlin.R
import com.example.espino.inventorykotlin.adapters.ListProductAdapter
import com.example.espino.inventorykotlin.interfaces.ProductPresenter
import com.example.espino.inventorykotlin.models.Product
import com.example.espino.inventorykotlin.presenters.ProductPresenterImpl

/**
 * Created by espino on 23/05/17.
 */

class ListProductFragment : ListFragment(), ProductPresenter.View{

    interface ListProductListener{
        fun onAddProductListener()
    }

    lateinit private var action: FloatingActionButton

    lateinit private var callback: ListProductListener
    lateinit private var presenter: ProductPresenterImpl
    lateinit private var adapter: ListProductAdapter

    lateinit private var product: Product

    companion object{
        fun newInstance(args: Bundle?): ListProductFragment{
            val fragment: ListProductFragment = ListProductFragment()
            if(args != null)
                fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            callback =  activity as ListProductListener
        }catch (ex: ClassCastException){
            throw ClassCastException(activity.toString() + "must implement interface ListProductListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ProductPresenterImpl(this)
        adapter = ListProductAdapter(context)
    }

    override fun onStart() {
        super.onStart()
        presenter.getAllProducts(loaderManager)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var v: View? = inflater?.inflate(R.layout.fragment_listproducts, container, false)
        action = v?.findViewById(R.id.productfragment_btn_add) as FloatingActionButton

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerForContextMenu(listView)

        listView.adapter = adapter
        action.setOnClickListener { callback.onAddProductListener() }//con LAMBDA

        /* action.setOnClickListener(object: View.OnClickListener{ con ANONYMOUS
            override fun onClick(p0: View?) {
                callback.onAddProductListener()
            }

        })*/
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var info = menuInfo as AdapterView.AdapterContextMenuInfo
        product = adapter.getItem(info.position)

        var inflater = activity.menuInflater
        menu?.setHeaderTitle(product.shortname)
        inflater.inflate(R.menu.context_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        super.onContextItemSelected(item)

        when(item?.itemId){
            R.id.contextmenu_delete -> {
                var dialog = AlertDialog.Builder(context)
                dialog.setTitle("are you sure?")
                        .setMessage("do you wnat to delete this product?")
                        .setPositiveButton("yes", { _, _ ->
                            presenter.deleteProduct(product)
                        })
                        .setNegativeButton("no", null)
                        .show()
            }

            R.id.contextmenu_edit -> {
            }
        }

        return true
    }

    override fun setCursor(c: Cursor?) {
        adapter.changeCursor(c)
    }



}
