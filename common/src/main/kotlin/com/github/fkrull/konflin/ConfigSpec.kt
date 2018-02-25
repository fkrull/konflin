package com.github.fkrull.konflin

import com.github.fkrull.konflin.impl.ConverterBasedSetting
import com.github.fkrull.konflin.typedescriptors.ConfigType
import com.github.fkrull.konflin.typedescriptors.IdentityTypeDescriptor
import kotlin.reflect.KClass

interface ConfigSpec {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    fun <T : Any> setting(
        type: KClass<T>,
        name: String,
        defaultValue: T? = null
    ): Setting<T> = when (type) {
        String::class -> ConverterBasedSetting<String>(name, defaultValue as String?, IdentityTypeDescriptor(ConfigType.Types.String))
        Int::class -> ConverterBasedSetting<Int>(name, defaultValue as Int?, IdentityTypeDescriptor(ConfigType.Types.Int))
        Boolean::class -> ConverterBasedSetting<Boolean>(name, defaultValue as Boolean?, IdentityTypeDescriptor(ConfigType.Types.Boolean))
        else -> throw UnsupportedConfigTypeException("cannot use '$type' as config type")
    } as Setting<T>
}

inline fun <reified T: Any> ConfigSpec.setting(name: String, defaultValue: T? = null): Setting<T>
    = setting(T::class, name, defaultValue)
