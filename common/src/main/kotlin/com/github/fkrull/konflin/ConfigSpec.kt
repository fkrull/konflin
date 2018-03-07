package com.github.fkrull.konflin

import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter
import com.github.fkrull.konflin.converter.impl.IdentityConverter
import com.github.fkrull.konflin.impl.ConverterBasedSetting
import com.github.fkrull.konflin.impl.KClassId
import com.github.fkrull.konflin.impl.id
import kotlin.reflect.KClass

private infix fun <T : Any> T?.orElse(other: () -> T): T = this ?: other()

interface ConfigSpec {
    private companion object {
        private val converters: Map<KClassId, Converter<*, *>> = mapOf(
            String::class.id to IdentityConverter(ConfigType.Types.String),
            Int::class.id to IdentityConverter(ConfigType.Types.Int),
            Boolean::class.id to IdentityConverter(ConfigType.Types.Boolean)
        )

        @Suppress("UNCHECKED_CAST")
        private fun <T : Any> getConverter(type: KClass<T>): Converter<T, Any>? = converters[type.id] as Converter<T, Any>?
    }

    fun <T : Any> setting(type: KClass<T>, name: String, defaultValue: T? = null): Setting<T> = getConverter(type)
        .orElse { throw UnsupportedConfigTypeException("cannot use '$type' as config type") }
        .let { createSetting(name, defaultValue, it) }

    private fun <T : Any> createSetting(name: String, defaultValue: T?, converter: Converter<T, Any>) =
        ConverterBasedSetting(name, defaultValue, converter)
}

inline fun <reified T : Any> ConfigSpec.setting(name: String, defaultValue: T? = null): Setting<T> =
    setting(T::class, name, defaultValue)

