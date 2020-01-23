package com.m7amdelbana.hanginkotlin.network.model

import com.google.gson.annotations.SerializedName

data class Account (
    @SerializedName("image") var image : String?=null,
    @SerializedName("blocked") var blocked : Boolean?=false,
    @SerializedName("isActive") var isActive : Boolean?=false,
    @SerializedName("_id") var _id : String?=null,
    @SerializedName("firstName") var firstName : String?=null,
    @SerializedName("lastName") var lastName : String?=null,
    @SerializedName("phone") var phone : Int?=0,
    @SerializedName("email") var email : String?=null,
    @SerializedName("created_at") var created_at : String?=null,
    @SerializedName("updated_at") var updated_at : String?=null,
    @SerializedName("__v") var __v : Int?=0
)
