package com.example.espino.inventorykotlin.presenters


import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import com.example.espino.inventorykotlin.bd.DatabaseContract
import com.example.espino.inventorykotlin.interfaces.ManageProductPresenter
import com.example.espino.inventorykotlin.models.Product
import com.example.espino.inventorykotlin.provider.InventoryProviderContract

/**
 * Created by espino on 24/05/17.
 */
class ManageProductPresenterImpl(var view: ManageProductPresenter.View) : ManageProductPresenter, LoaderManager.LoaderCallbacks<Cursor>{

    val CURSOR_CATEGORY: Int = 10
    val CURSOR_SUBCATEGORY: Int = 11

    override fun getAllCategories(lm: LoaderManager) {
        lm.initLoader(CURSOR_CATEGORY, null, this)
    }

    override fun getAllSubcategories(lm: LoaderManager, args: Bundle) {
        lm.restartLoader(CURSOR_SUBCATEGORY, args, this)
    }

    override fun addProduct(p: Product) {
        var cv = ContentValues()
        cv.put(DatabaseContract.ProductEntry.COLUMN_SERIAL, p.serial)
        cv.put(DatabaseContract.ProductEntry.COLUMN_SHORTNAME, p.shortname)
        cv.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, p.description)
        cv.put(DatabaseContract.ProductEntry.COLUMN_CATEGORY, p.category)
        cv.put(DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY, p.subcategory)
        cv.put(DatabaseContract.ProductEntry.COLUMN_PRODUCTCLASS, p.productClass)

        view.getContext().contentResolver.insert(InventoryProviderContract.ProductEntry.CONTENT_URI, cv)
    }

    override fun editProduct(p: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor>? {
        var loader: Loader<Cursor>? = null
        when(id){
            CURSOR_CATEGORY-> loader = CursorLoader(view.getContext(),InventoryProviderContract.CategoryEntry.CONTENT_URI, DatabaseContract.CategoryEntry.ALL_COLUMNS, null, null, null)

            CURSOR_SUBCATEGORY->loader = CursorLoader(view.getContext(), InventoryProviderContract.SubCategoryEntry.CONTENT_URI, DatabaseContract.SubCategoryEntry.ALL_COLUMNS, "categoryid=?", arrayOf(args?.getString("id")), null)
            }

        return loader
    }


    override fun onLoadFinished(loader: Loader<Cursor>?, cursor: Cursor?) {
        if(loader?.id == CURSOR_CATEGORY)
            view.setCategorySpnAdapter(cursor)
        else
            view.setSubcategorySpnAdapter(cursor)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        if(loader?.id == CURSOR_CATEGORY)
            view.setCategorySpnAdapter(null)
        else
            view.setSubcategorySpnAdapter(null)
    }

}