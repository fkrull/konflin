package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.Configuration
import com.github.fkrull.konflin.ConfigurationSource
import com.github.fkrull.konflin.MissingConfigValueException
import com.github.fkrull.konflin.Setting

internal class DelegatingConfiguration(private val configSource: ConfigurationSource) : Configuration {
    override operator fun <T> get(setting: Setting<T>): T = getOrDefault(setting)
            ?: throw MissingConfigValueException("missing value for required setting '${setting.name}'")

    override operator fun <T> contains(setting: Setting<T>): Boolean = getOrDefault(setting) != null

    private fun <T> getOrDefault(setting: Setting<T>) =
            setting.get(configSource) ?: setting.default
}
