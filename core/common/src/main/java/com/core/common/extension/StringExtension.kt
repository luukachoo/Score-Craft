package com.core.common.extension

fun String.getStreamPreview(quality: String): String {
    val list = this.split("=")
    return "https://static-cdn.jtvnw.net/previews-ttv/live_user_${list.last()}-$quality.jpg"
}

fun String.convertDate() : String {
    return this.dropLast(10)
}

fun String.convertTime() : String {
    return this.drop(11).dropLast(4)
}