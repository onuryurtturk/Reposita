package com.onuryurtturk.reposita.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner(
    /**
     * Repository's Owner data class
     */
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("avatar_url") val avatar_url: String,
    @field:SerializedName("gravatar_id") val gravatar_id: String?,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("repos_url") val repos_url: String,
    @field:SerializedName("starred_url") val starred_url: String )  : Serializable