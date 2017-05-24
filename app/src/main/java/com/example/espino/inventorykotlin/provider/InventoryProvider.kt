package com.example.espino.inventorykotlin.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import com.example.espino.inventorykotlin.bd.DatabaseContract
import com.example.espino.inventorykotlin.bd.DatabaseHelper

/**
 * Created by espino on 24/05/17.
 */

class InventoryProvider : ContentProvider(){

    val PRODUCT = 1
    val PRODUCT_ID = 2
    val CATEGORY = 3
    val CATEGORY_ID = 4
    val SUBCATEGORY = 5
    val SUBCATEGORY_ID = 6

    val uriMatcher : UriMatcher = createUriMatcher()
    lateinit var db: SQLiteDatabase

    private fun createUriMatcher() : UriMatcher{
        var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        val AUTHORITY = InventoryProviderContract.AUTHORITY

        uriMatcher.addURI(AUTHORITY, InventoryProviderContract.ProductEntry.CONTENT_PATH, PRODUCT)
        uriMatcher.addURI(AUTHORITY, InventoryProviderContract.ProductEntry.CONTENT_PATH + "/#", PRODUCT_ID)
        uriMatcher.addURI(AUTHORITY, InventoryProviderContract.CategoryEntry.CONTENT_PATH, CATEGORY)
        uriMatcher.addURI(AUTHORITY, InventoryProviderContract.CategoryEntry.CONTENT_PATH + "/#", CATEGORY_ID)
        uriMatcher.addURI(AUTHORITY, InventoryProviderContract.SubCategoryEntry.CONTENT_PATH, SUBCATEGORY)
        uriMatcher.addURI(AUTHORITY, InventoryProviderContract.SubCategoryEntry.CONTENT_PATH + "/#", SUBCATEGORY_ID)

        return uriMatcher
    }

    override fun onCreate(): Boolean {
        db = DatabaseHelper.opendatabase()
        return true
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val queryBuilder = SQLiteQueryBuilder()

        when(uriMatcher.match(uri)){
            PRODUCT->{
                queryBuilder.tables = """${DatabaseContract.ProductEntry.TABLE_NAME} JOIN ${DatabaseContract.CategoryEntry.TABLE_NAME}
                ON ${DatabaseContract.ProductEntry.TABLE_NAME}.${DatabaseContract.ProductEntry.COLUMN_CATEGORY} = ${DatabaseContract.CategoryEntry.TABLE_NAME}.${DatabaseContract.CategoryEntry.COLUMN_ID}
                JOIN ${DatabaseContract.SubCategoryEntry.TABLE_NAME}
                ON ${DatabaseContract.ProductEntry.TABLE_NAME}.${DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY} = ${DatabaseContract.SubCategoryEntry.TABLE_NAME}.${DatabaseContract.SubCategoryEntry.COLUMN_ID}"""


                //queryBuilder.setProjectionMap(InventoryProviderContract.ProductEntry.productProjectionMap) TODO FALLA
            }
            PRODUCT_ID->{

            }
            CATEGORY->{
                queryBuilder.tables= DatabaseContract.CategoryEntry.TABLE_NAME
            }
            CATEGORY_ID->{

            }
            SUBCATEGORY->{
                queryBuilder.tables = DatabaseContract.SubCategoryEntry.TABLE_NAME
            }
            SUBCATEGORY_ID->{

            }
        }

        return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun insert(uri: Uri?, cv: ContentValues?): Uri {
        var id: Long = -1

        when(uriMatcher.match(uri)){
            PRODUCT->{
                id = db.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, cv)
            }
            PRODUCT_ID->{

            }
            CATEGORY->{

            }
            CATEGORY_ID->{

            }
            SUBCATEGORY->{

            }
            SUBCATEGORY_ID->{

            }
        }
        return ContentUris.withAppendedId(uri, id)
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        var id: Int = -1

        when(uriMatcher.match(uri)){
            PRODUCT->{
               db.delete(DatabaseContract.ProductEntry.TABLE_NAME, selection, selectionArgs)
            }
            PRODUCT_ID->{

            }
            CATEGORY->{

            }
            CATEGORY_ID->{

            }
            SUBCATEGORY->{

            }
            SUBCATEGORY_ID->{

            }
        }
        return id
    }

    override fun getType(p0: Uri?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
