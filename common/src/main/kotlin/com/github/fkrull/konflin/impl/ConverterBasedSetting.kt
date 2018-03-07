package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.ConfigurationSource
import com.github.fkrull.konflin.Setting
import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter

internal class ConverterBasedSetting<out T : Any>(
    override val name: String,
    override val default: T?,
    private val converter: Converter<T, Any>
) : Setting<T> {
    override fun get(configSource: ConfigurationSource): T? = when (converter.configType) {
        ConfigType.Types.String -> configSource.getString(name)?.let { converter.fromConfig(it) }
        ConfigType.Types.Int -> configSource.getInt(name)?.let { converter.fromConfig(it) }
        ConfigType.Types.Boolean -> configSource.getBoolean(name)?.let { converter.fromConfig(it) }
    }
}
