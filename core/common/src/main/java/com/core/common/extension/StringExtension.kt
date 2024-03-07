package com.core.common.extension

fun String.getStreamPreview(quality: String): String {
    val list = this.split("=")
    return "https://static-cdn.jtvnw.net/previews-ttv/live_user_${list.last()}-$quality.jpg"
}