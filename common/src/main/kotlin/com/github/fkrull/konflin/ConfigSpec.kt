package com.github.fkrull.konflin

import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter
import com.github.fkrull.konflin.converter.impl.IdentityConverter
import com.github.fkrull.konflin.impl.ConverterBasedSetting
import com.github.fkrull.konflin.impl.KClassId
import com.github.fkrull.konflin.impl.id
import kotlin.reflect.KClass

private infix fun <T : Any> T?.orElse(other: () -> T): T = this ?: other()

abstract class ConfigSpec(vararg additionalConverters: Converter<*, *>) {
    inline fun <reified T : Any> setting(name: String, defaultValue: T? = null): Setting<T> = setting(T::class, name, defaultValue)

    fun <T : Any> setting(type: KClass<T>, name: String, defaultValue: T? = null): Setting<T> = getConverter(type)
        .orElse { throw UnsupportedConfigTypeException("cannot use '$type' as config type") }
        .let { ConverterBasedSetting(name, defaultValue, it) }

    private companion object {
        private val DEFAULT_CONVERTERS: List<Converter<*, *>> = listOf(
            IdentityConverter(ConfigType.Types.String),
            IdentityConverter(ConfigType.Types.Int),
            IdentityConverter(ConfigType.Types.Boolean)
        )
    }

    private val converters: Map<KClassId, Converter<*, *>> = (DEFAULT_CONVERTERS + additionalConverters)
        .map { converter -> converter.outType.id to converter }
        .toMap()

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> getConverter(type: KClass<T>): Converter<T, Any>? = converters[type.id] as Converter<T, Any>?
}
