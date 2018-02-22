package com.github.fkrull.konflin

import com.github.fkrull.konflin.impl.DelegatingSetting
import kotlin.reflect.KClass

interface ConfigSpec {
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> setting(
        type: KClass<T>,
        name: String,
        defaultValue: T? = null
    ): Setting<T> = when (type) {
        String::class -> DelegatingSetting(name, { it.getString(name) }, { defaultValue })
        Int::class -> DelegatingSetting(name, { it.getInt(name) }, { defaultValue })
        Boolean::class -> DelegatingSetting(name, { it.getBoolean(name) }, { defaultValue })
        else -> throw UnsupportedConfigTypeException("cannot use '$type' as config type")
    } as Setting<T>
}

inline fun <reified T: Any> ConfigSpec.setting(name: String, defaultValue: T? = null): Setting<T>
    = setting(T::class, name, defaultValue)
