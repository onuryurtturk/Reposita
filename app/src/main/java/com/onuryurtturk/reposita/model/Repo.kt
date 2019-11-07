package com.onuryurtturk.reposita.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repo(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val full_name: String,
    @field:SerializedName("private") val private: Boolean,
    @field:SerializedName("owner") val owner: Owner,
    @field:SerializedName("html_url") val html_url: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("stargazers_count") val stargazers_count: Int,
    @field:SerializedName("watchers_count") val watchers_count: Int,
    @field:SerializedName("language") val language: String,
    @field:SerializedName("has_issues") val has_issues: Boolean,
    @field:SerializedName("forks_count") val forks_count: Int,
    @field:SerializedName("open_issues_count") val open_issues_count: Int,
    @field:SerializedName("forks") val forks: Int,
    @field:SerializedName("open_issues") val open_issues: Int,
    @field:SerializedName("watchers") val watchers: Int)  : Serializable {

}