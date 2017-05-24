package com.example.espino.inventorykotlin.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by espino on 23/05/17.
 */

data class Product(var id: Int, var serial: String, var shortname: String, var description: String, var category: String, var subcategory: String, var productClass: String) : Parcelable{
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Product> = object : Parcelable.Creator<Product> {
            override fun createFromParcel(source: Parcel): Product = Product(source)
            override fun newArray(size: Int): Array<Product?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(serial)
        dest.writeString(shortname)
        dest.writeString(description)
        dest.writeString(category)
        dest.writeString(subcategory)
        dest.writeString(productClass)
    }
}