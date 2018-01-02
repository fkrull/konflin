package com.github.fkrull.konflin

interface Setting<out T> {
    val name: String
    val default: T?
    fun get(configSource: ConfigurationSource): T?
}
