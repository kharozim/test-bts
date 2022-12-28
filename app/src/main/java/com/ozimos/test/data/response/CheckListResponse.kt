package com.ozimos.test.data.response

import com.google.gson.annotations.SerializedName

data class CheckListResponse(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("items")
    val items: Items? = null,

    @field:SerializedName("checklistCompletionStatus")
    val checklistCompletionStatus: Boolean? = null
)

data class Items(

    @field:SerializedName("itemCompletionStatus")
    val itemCompletionStatus: Boolean? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
