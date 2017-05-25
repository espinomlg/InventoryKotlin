package com.example.espino.inventorykotlin.presenters


import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import com.example.espino.inventorykotlin.bd.DatabaseContract
import com.example.espino.inventorykotlin.interfaces.ProductPresenter
import com.example.espino.inventorykotlin.models.Product
import com.example.espino.inventorykotlin.provider.InventoryProviderContract


/**
 * Created by espino on 23/05/17.
 */
class ProductPresenterImpl(var view: ProductPresenter.View) : ProductPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    val CURSOR_ID: Int = 1

    override fun getAllProducts(lm: LoaderManager) {
        lm.initLoader(CURSOR_ID, null, this)
    }

    override fun deleteProduct(p: Product) {
        view.getContext().contentResolver.delete(InventoryProviderContract.ProductEntry.CONTENT_URI, "_id=?", arrayOf(p.id.toString()))
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor>? {
        var loader: Loader<Cursor>? = null
        val projection: Array<String> =  arrayOf("product." + DatabaseContract.ProductEntry.COLUMN_ID, DatabaseContract.ProductEntry.COLUMN_SERIAL,
                DatabaseContract.ProductEntry.COLUMN_SHORTNAME, "product." + DatabaseContract.ProductEntry.COLUMN_DESCRIPTION,
                "category." + DatabaseContract.CategoryEntry.COLUMN_NAME, "subcategory." + DatabaseContract.SubCategoryEntry.COLUMN_NAME,
                DatabaseContract.ProductEntry.COLUMN_PRODUCTCLASS)

        when(p0){
            CURSOR_ID-> loader = CursorLoader(view.getContext(), InventoryProviderContract.ProductEntry.CONTENT_URI, projection, null, null, null)
        }

        return loader
    }

    override fun onLoadFinished(p0: Loader<Cursor>?, cursor: Cursor?) {
        cursor?.setNotificationUri(view.getContext().contentResolver, InventoryProviderContract.ProductEntry.CONTENT_URI)
        view.setCursor(cursor)
    }

    override fun onLoaderReset(p0: Loader<Cursor>?) {
        view.setCursor(null)
    }





}