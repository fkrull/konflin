package com.github.fkrull.konflin

import com.github.fkrull.konflin.impl.DelegatingSetting

interface ConfigSpec

@Suppress("unused")
fun <T> ConfigSpec.setting(
        name: String,
        configDelegate: (configSource: ConfigurationSource) -> T?,
        defaultValueDelegate: () -> T?
): Setting<T> = DelegatingSetting(name, configDelegate, defaultValueDelegate)

@Suppress("UNCHECKED_CAST", "unused")
inline fun <reified T: Any> ConfigSpec.setting(name: String, defaultValue: T? = null): Setting<T> =
    when (T::class) {
        String::class -> setting(name, { it.getString(name) }, { defaultValue })
        Int::class -> setting(name, { it.getInt(name) }, { defaultValue })
        Boolean::class -> setting(name, { it.getBoolean(name) }, { defaultValue })
        else -> throw UnsupportedConfigTypeException("cannot use '${T::class}' as config type")
    } as Setting<T>
