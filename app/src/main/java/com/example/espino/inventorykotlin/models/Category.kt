package com.example.espino.inventorykotlin.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by espino on 23/05/17.
 */
data class Category(var id: Int, var name: String, var shortname: String, var description: String) : Parcelable{
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Category> = object : Parcelable.Creator<Category> {
            override fun createFromParcel(source: Parcel): Category = Category(source)
            override fun newArray(size: Int): Array<Category?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(shortname)
        dest.writeString(description)
    }
}
