package com.example.espino.inventorykotlin.bd



import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.espino.inventorykotlin.InventoryApplication

/**
 * Created by espino on 23/05/17.
 */

object DatabaseHelper: SQLiteOpenHelper(InventoryApplication.context, "Inventory.db", null, 1) {

    fun opendatabase(): SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {

        try {
            db?.beginTransaction()

            db?.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRY)
            db?.execSQL(DatabaseContract.SubCategoryEntry.SQL_CREATE_ENTRY)
            db?.execSQL(DatabaseContract.ProductEntry.SQL_CREATE_ENTRY)
            db?.execSQL(DatabaseContract.ProductClassEntry.SQL_CREATE_ENTRY)

            db?.execSQL("INSERT INTO product(serial,code,description,category,subcategory,productclass) VALUES " +
                    "('001', 'Monitor', 'Monitor del ordenador', 1, 1, 1), " +
                    "('002', 'Teclado', 'Teclado del ordenador', 1, 1, 1), " +
                    "('003', 'Ratón', 'Ratón del ordenador', 1, 1, 1);")
            db?.execSQL("insert into category(name, sortname, description) values\n" +
                    "('periféricos', 'per', 'suplementos para el pc'),\n" +
                    "('componentes', 'com', 'componentes para el pc');")
            db?.execSQL("insert into subcategory(categoryid, name, sortname, description)\n" +
                    "values (1, 'pantallas', 'pant', 'monitores para pc'),\n" +
                    "(1, 'ratones', 'rat', 'ratones para pc'),\n" +
                    "(2, 'microprocesadores', 'micro', 'procesadores para pc'),\n" +
                    "(2, 'memoria RAM', 'RAM', 'memoria RAM para pc');")
            db?.execSQL("insert into productclass(description) values\n" +
                    "('algo'),\n" +
                    "('que no se que es');")

            db?.setTransactionSuccessful()
        }catch (ex: SQLiteException){
            Log.e("DATABASE ERROR", ex.localizedMessage)
        }finally {
            db?.endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        try {
            db?.beginTransaction();

            db?.execSQL(DatabaseContract.CategoryEntry.SQL_DELETE_ENTRY)
            db?.execSQL(DatabaseContract.SubCategoryEntry.SQL_DELETE_ENTRY)
            db?.execSQL(DatabaseContract.ProductEntry.SQL_DELETE_ENTRY)
            db?.execSQL(DatabaseContract.ProductClassEntry.SQL_DELETE_ENTRY)
            onCreate(db)
            db?.setTransactionSuccessful()
        } catch (ex: SQLiteException) {
            Log.e("DATABASE ERROR", ex.localizedMessage)
        } finally {
            db?.endTransaction()
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        onUpgrade(db, newVersion, oldVersion)
    }

}
