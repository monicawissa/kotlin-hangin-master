package com.m7amdelbana.hanginkotlin.network.model

import com.google.gson.annotations.SerializedName

data class token(
    @SerializedName("x-auth-token")
    var x_auth_token: String? = null
)