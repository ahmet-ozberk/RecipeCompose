package com.ao.recipeapp.app.model

data class MediaSelectModel(val mediaPath:String, val mediaType: MediaType = MediaType.IMAGE)

enum class MediaType {
    IMAGE,
    VIDEO
}