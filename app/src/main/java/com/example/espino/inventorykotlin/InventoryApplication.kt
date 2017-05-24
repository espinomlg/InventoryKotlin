package com.example.espino.inventorykotlin

import android.app.Application
import android.content.Context
import com.example.espino.inventorykotlin.bd.DatabaseHelper

/**
 * Created by espino on 23/05/17.
 */

class InventoryApplication: Application(){
    companion object{
        lateinit var context: InventoryApplication
            private set
    }
    init {
        context = this
    }



    override fun onCreate() {
        super.onCreate()
        DatabaseHelper.opendatabase()
    }
}
