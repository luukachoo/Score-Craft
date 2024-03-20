package com.core.common.resource

abstract class Recyclable<T> {
    abstract val uniqueValue: T

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Recyclable<*>) return false
        return uniqueValue == other.uniqueValue
    }

    override fun hashCode(): Int {
        return uniqueValue.hashCode()
    }
}
