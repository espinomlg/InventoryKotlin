package com.example.espino.inventorykotlin.interfaces


import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager

import com.example.espino.inventorykotlin.models.Product


/**
 * Created by espino on 8/05/17.
 */

interface ManageProductPresenter {

    fun getAllCategories(lm: LoaderManager)
    fun getAllSubcategories(lm: LoaderManager, args: Bundle)

    fun addProduct(p: Product)
    fun editProduct(p: Product)

    interface View {
        fun setCategorySpnAdapter(c: Cursor?)
        fun setSubcategorySpnAdapter(c: Cursor?)
        fun getContext(): Context
    }
}
