package com.github.fkrull.konflin

import com.github.fkrull.konflin.impl.ConverterBasedSetting
import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter
import com.github.fkrull.konflin.converter.impl.IdentityConverter
import kotlin.reflect.KClass

interface ConfigSpec {
    @Suppress("RemoveExplicitTypeArguments", "UNCHECKED_CAST")
    fun <T : Any> setting(
        type: KClass<T>,
        name: String,
        defaultValue: T? = null
    ): Setting<T> = when (type) {
        String::class -> ConverterBasedSetting<String>(name, defaultValue as String?, IdentityConverter(ConfigType.Types.String) as Converter<String, Any>)
        Int::class -> ConverterBasedSetting<Int>(name, defaultValue as Int?, IdentityConverter(ConfigType.Types.Int) as Converter<Int, Any>)
        Boolean::class -> ConverterBasedSetting<Boolean>(name, defaultValue as Boolean?, IdentityConverter(ConfigType.Types.Boolean) as Converter<Boolean, Any>)
        else -> throw UnsupportedConfigTypeException("cannot use '$type' as config type")
    } as Setting<T>
}

inline fun <reified T: Any> ConfigSpec.setting(name: String, defaultValue: T? = null): Setting<T>
    = setting(T::class, name, defaultValue)
