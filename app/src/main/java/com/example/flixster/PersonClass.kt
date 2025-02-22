package com.example.flixster

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PersonClass(

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("profile_path")
    var profile_path: String? = null,

    @SerializedName("known_for")
    var knownFor: List<KnownFor>? = null

) {
    fun getProfileUrl(): String {
        return "https://image.tmdb.org/t/p/w500$profile_path"
    }
}
data class KnownFor(
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("overview") val overview: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnownFor> {
        override fun createFromParcel(parcel: Parcel): KnownFor {
            return KnownFor(parcel)
        }

        override fun newArray(size: Int): Array<KnownFor?> {
            return arrayOfNulls(size)
        }
    }
}