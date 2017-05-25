package com.example.espino.inventorykotlin.provider

import android.net.Uri
import android.provider.BaseColumns
import com.example.espino.inventorykotlin.bd.DatabaseContract
import java.util.*


/**
 * Created by espino on 18/05/17.
 */

object InventoryProviderContract {
    val AUTHORITY = "com.example.espino.inventorykotlin"
    val AUTHORITY_URI: Uri = Uri.parse("content://" + AUTHORITY)


    object ProductEntry{
            val CONTENT_PATH = "product"
            val CONTENT_URI: Uri = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH)
            val _ID = BaseColumns._ID
            val SERIAL = "serial"
            val SHORTNAME = "code"
            val DESCRIPTION = "description"
            val CATEGORY = "category"
            val SUBCATEGORY = "subcategory"
            val PRODUCTCLASS = "productclass"

            val PROJECTION = arrayOf(BaseColumns._ID, SERIAL, SHORTNAME, DESCRIPTION, CATEGORY, SUBCATEGORY, PRODUCTCLASS)

            val productProjectionMap = HashMap<String, String>()

            init {
                productProjectionMap.put(_ID, DatabaseContract.ProductEntry.COLUMN_ID)
                productProjectionMap.put(SERIAL, DatabaseContract.ProductEntry.COLUMN_SERIAL)
                productProjectionMap.put(SHORTNAME, DatabaseContract.ProductEntry.COLUMN_SHORTNAME)
                productProjectionMap.put(DESCRIPTION, DatabaseContract.ProductEntry.COLUMN_DESCRIPTION )
                productProjectionMap.put(CATEGORY, DatabaseContract.ProductEntry.COLUMN_CATEGORY )
                productProjectionMap.put(SUBCATEGORY, DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY )
                productProjectionMap.put(PRODUCTCLASS, DatabaseContract.ProductEntry.COLUMN_PRODUCTCLASS )
            }


    }

    object CategoryEntry{
            val CONTENT_PATH = "category"
            val CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH)
            val _ID = BaseColumns._ID
            val NAME = "name"
            val SORTNAME = "sortname"
            val DESCRIPTION = "description"

            val PROJECTION = arrayOf(_ID, NAME, SORTNAME, DESCRIPTION)

    }

    object SubCategoryEntry{
            val CONTENT_PATH = "subcategory"
            val CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH)
            val _ID = BaseColumns._ID
            val CATEGORYID = "categoryid"
            val NAME = "name"
            val SORTNAME = "sortname"
            val DESCRIPTION = "description"

            val PROJECTION = arrayOf(_ID, CATEGORYID, NAME, SORTNAME, DESCRIPTION)

    }

    object ProductClassEntry{
            val CONTENT_PATH = "productclass"
            val CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH)
            val _ID = BaseColumns._ID
            val DESCRIPTION = "description"

            val PROJECTION = arrayOf(_ID, DESCRIPTION)

    }
}
