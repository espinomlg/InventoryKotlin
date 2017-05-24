package com.example.espino.inventorykotlin.interfaces

import android.content.Context
import android.database.Cursor
import android.support.v4.app.LoaderManager

import com.example.espino.inventorykotlin.models.Product


/**
 * Created by espino on 24/04/17.
 */

interface ProductPresenter {

    fun getAllProducts(lm: LoaderManager)
    fun deleteProduct(p: Product)

    interface View {
        fun setCursor(c: Cursor?)
        fun getContext(): Context
    }

}
