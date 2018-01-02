package com.github.fkrull.konflin

import com.github.fkrull.konflin.impl.DelegatingConfiguration

interface Configuration {
    operator fun <T> get(setting: Setting<T>): T
    operator fun <T> contains(setting: Setting<T>): Boolean
}

fun configuration(configSource: ConfigurationSource): Configuration = DelegatingConfiguration(configSource)
