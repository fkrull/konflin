package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.ConfigurationSource
import com.github.fkrull.konflin.Setting
import com.github.fkrull.konflin.typedescriptors.ConfigType
import com.github.fkrull.konflin.typedescriptors.TypeDescriptor

internal class ConverterBasedSetting<out T : Any>(
    override val name: String,
    override val default: T?,
    converter: TypeDescriptor<T, *>
) : Setting<T> {
    @Suppress("UNCHECKED_CAST")
    private val converter: TypeDescriptor<T, Any> = converter as TypeDescriptor<T, Any>

    override fun get(configSource: ConfigurationSource): T? {
        return when (converter.configType) {
            ConfigType.Types.String -> configSource.getString(name)?.let { converter.fromConfig(it) }
            ConfigType.Types.Int -> configSource.getInt(name)?.let { converter.fromConfig(it) }
            ConfigType.Types.Boolean -> configSource.getBoolean(name)?.let { converter.fromConfig(it) }
        }
    }
}
