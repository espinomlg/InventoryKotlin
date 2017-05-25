package com.example.espino.inventorykotlin.fragments

import android.app.Activity
import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.espino.inventorykotlin.R
import com.example.espino.inventorykotlin.bd.DatabaseContract
import com.example.espino.inventorykotlin.interfaces.ManageProductPresenter
import com.example.espino.inventorykotlin.models.Category
import com.example.espino.inventorykotlin.models.Product
import com.example.espino.inventorykotlin.models.Subcategory
import com.example.espino.inventorykotlin.presenters.ManageProductPresenterImpl

/**
 * Created by espino on 24/05/17.
 */
class ManageProductFragment : Fragment(), ManageProductPresenter.View{

    interface ManageProductListener{
        fun onManageProductListener()
    }

    lateinit var serial: TextInputLayout
    lateinit var shortname: TextInputLayout
    lateinit var description: TextInputLayout

    lateinit var category: Spinner
    lateinit var subcategory: Spinner
    lateinit var productClass: Spinner

    lateinit var action: Button

    lateinit var callback: ManageProductListener
    lateinit var presenter: ManageProductPresenter
    lateinit var categoryAdapter: SimpleCursorAdapter
    lateinit var subcategoryAdapter: SimpleCursorAdapter

    companion object{
        fun newInstance(args: Bundle?): ManageProductFragment{
            val fragment = ManageProductFragment()
            if(args != null)
                fragment.arguments = args

            return fragment
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try{
            callback = activity as ManageProductListener
        }catch(ex: ClassCastException){
            throw ClassCastException(activity.toString() + " must implement interface ManageProductListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ManageProductPresenterImpl(this)

        categoryAdapter = SimpleCursorAdapter(context, android.R.layout.simple_spinner_item, null, arrayOf(DatabaseContract.CategoryEntry.COLUMN_NAME),
                intArrayOf(android.R.id.text1), CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        subcategoryAdapter = SimpleCursorAdapter(context, android.R.layout.simple_spinner_item, null, arrayOf(DatabaseContract.SubCategoryEntry.COLUMN_NAME),
                intArrayOf(android.R.id.text1), CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
    }

    override fun onStart() {
        super.onStart()
        presenter.getAllCategories(loaderManager)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var v: View? = inflater?.inflate(R.layout.fragment_addproduct, container, false)
        serial =  v?.findViewById(R.id.addpoduct_serial) as TextInputLayout
        shortname =  v.findViewById(R.id.addpoduct_shortname) as TextInputLayout
        description =  v.findViewById(R.id.addpoduct_description) as TextInputLayout

        category = v.findViewById(R.id.addproduct_spn_category) as Spinner
        subcategory = v.findViewById(R.id.addproduct_spn_subcategory) as Spinner
        productClass = v.findViewById(R.id.addproduct_spn_productclass) as Spinner

        action = v.findViewById(R.id.addproduct_btn_action) as Button

        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category.adapter = categoryAdapter
        subcategory.adapter = subcategoryAdapter
        productClass.adapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, resources.getStringArray(R.array.productclass))

        category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var args = Bundle()
                args.putString("id", categoryAdapter.getMyCategory(p2).id.toString())
                presenter.getAllSubcategories(loaderManager, args)
            }

        }

        action.setOnClickListener {
            presenter.addProduct(getProduct())
            callback.onManageProductListener()
        }
    }

    override fun setCategorySpnAdapter(c: Cursor?) {
        categoryAdapter.changeCursor(c)
    }

    override fun setSubcategorySpnAdapter(c: Cursor?) {
        subcategoryAdapter.changeCursor(c)
    }

    private fun SimpleCursorAdapter.getMyCategory(pos: Int) : Category{
        cursor.moveToPosition(pos)
        var category = Category(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3))

        return category
    }

    private fun SimpleCursorAdapter.getMySubcategory(pos: Int) : Subcategory{
        cursor.moveToPosition(pos)
        var subcategory = Subcategory(cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3))

        return subcategory
    }

    private fun getProduct(): Product = Product(-1, serial.editText?.text?.toString() ?: "nulo",
            shortname.editText?.text?.toString() ?: "nulo",
            description.editText?.text?.toString() ?: "nulo",
            categoryAdapter.getMyCategory(category.selectedItemPosition).id.toString(),
            subcategoryAdapter.getMySubcategory(subcategory.selectedItemPosition).id.toString(),
            productClass.selectedItemPosition.toString())



}