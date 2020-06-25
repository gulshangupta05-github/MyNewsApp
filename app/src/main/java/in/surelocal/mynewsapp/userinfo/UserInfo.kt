package `in`.surelocal.mynewsapp.userinfo

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfo(
    @SerializedName("document_id")
    val document_id:String="",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("location")
    val location: String = "",

//    @SerializedName("latitude")
//    val latitude: FusedLocationProviderClient,

//    @SerializedName("fusedLocationClient")
//    val lontitude: FusedLocationProviderClient,

    @SerializedName("currenttime")
    val currenttime: Long,

    @SerializedName("postalcode")
    val postalcode: String = ""
) : Serializable