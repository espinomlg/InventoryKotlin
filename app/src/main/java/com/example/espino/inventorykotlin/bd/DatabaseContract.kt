package com.example.espino.inventorykotlin.bd

import android.provider.BaseColumns

/**
 * Created by espino on 20/04/17.
 */

object DatabaseContract {

    object ProductEntry {
            val TABLE_NAME = "product"
            val COLUMN_ID = BaseColumns._ID
            val COLUMN_SERIAL = "serial"
            val COLUMN_SHORTNAME = "code"
            val COLUMN_DESCRIPTION = "description"
            val COLUMN_CATEGORY = "category"
            val COLUMN_SUBCATEGORY = "subcategory"
            val COLUMN_PRODUCTCLASS = "productclass"

            val SQL_CREATE_ENTRY = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT UNIQUE NOT NULL," +
                    "%s TEXT UNIQUE NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s INT NOT NULL," +
                    "%s INT NOT NULL," +
                    "%s INT NOT NULL)",
                    TABLE_NAME,
                    COLUMN_ID,
                    COLUMN_SERIAL,
                    COLUMN_SHORTNAME,
                    COLUMN_DESCRIPTION,
                    COLUMN_CATEGORY,
                    COLUMN_SUBCATEGORY,
                    COLUMN_PRODUCTCLASS)

            val SQL_DELETE_ENTRY = String.format("DROP TABLE %s", TABLE_NAME)

            val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_SERIAL, COLUMN_SHORTNAME, COLUMN_DESCRIPTION, COLUMN_CATEGORY, COLUMN_SUBCATEGORY, COLUMN_PRODUCTCLASS)


    }

    object CategoryEntry {
            val TABLE_NAME = "category"
            val COLUMN_ID = BaseColumns._ID
            val COLUMN_NAME = "name"
            val COLUMN_SORTNAME = "sortname"
            val COLUMN_DESCRIPTION = "description"

            val SQL_CREATE_ENTRY = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT UNIQUE NOT NULL," +
                    "%s TEXT UNIQUE NOT NULL," +
                    "%s TEXT NOT NULL)",
                    TABLE_NAME,
                    COLUMN_ID,
                    COLUMN_NAME,
                    COLUMN_SORTNAME,
                    COLUMN_DESCRIPTION)

            val SQL_DELETE_ENTRY = String.format("DROP TABLE %s", TABLE_NAME)
            val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_SORTNAME, COLUMN_DESCRIPTION)

    }

    object SubCategoryEntry{
            val TABLE_NAME = "subcategory"
            val COLUMN_ID = BaseColumns._ID
            val COLUMN_CATEGORYID = "categoryid"
            val COLUMN_NAME = "name"
            val COLUMN_SORTNAME = "sortname"
            val COLUMN_DESCRIPTION = "description"

            val SQL_CREATE_ENTRY = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s INTEGER NOT NULL," +
                    "%s TEXT UNIQUE NOT NULL," +
                    "%s TEXT UNIQUE NOT NULL," +
                    "%s TEXT NOT NULL)",
                    TABLE_NAME,
                    COLUMN_ID,
                    COLUMN_CATEGORYID,
                    COLUMN_NAME,
                    COLUMN_SORTNAME,
                    COLUMN_DESCRIPTION)

            val SQL_DELETE_ENTRY = String.format("DROP TABLE %s", TABLE_NAME)

            val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_CATEGORYID, COLUMN_NAME, COLUMN_SORTNAME, COLUMN_DESCRIPTION)

    }

    object ProductClassEntry {
            val TABLE_NAME = "productclass"
            val COLUMN_ID = BaseColumns._ID
            val COLUMN_DESCRIPTION = "description"

            val SQL_CREATE_ENTRY = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL)",
                    TABLE_NAME,
                    COLUMN_ID,
                    COLUMN_DESCRIPTION)

            val SQL_DELETE_ENTRY = String.format("DROP TABLE %s", TABLE_NAME)

            val ALL_COLUMNS = arrayOf(COLUMN_ID, COLUMN_DESCRIPTION)

    }
}
