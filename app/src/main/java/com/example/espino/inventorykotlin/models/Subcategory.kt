package com.example.espino.inventorykotlin.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by espino on 23/05/17.
 */
data class Subcategory(var id: Int, var categoryId: Int, var name: String, var shortname: String, var description: String) : Parcelable{
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Subcategory> = object : Parcelable.Creator<Subcategory> {
            override fun createFromParcel(source: Parcel): Subcategory = Subcategory(source)
            override fun newArray(size: Int): Array<Subcategory?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readInt(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeInt(categoryId)
        dest.writeString(name)
        dest.writeString(shortname)
        dest.writeString(description)
    }
}