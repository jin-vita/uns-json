package org.techtown.unsjson

import com.google.gson.annotations.SerializedName

data class Options(
    @SerializedName("option_A") val optionA: String,
    @SerializedName("option_B") val optionB: String,
    @SerializedName("option_C") val optionC: String,
    @SerializedName("option_D") val optionD: String,
    @SerializedName("option_E") val optionE: String,
)
