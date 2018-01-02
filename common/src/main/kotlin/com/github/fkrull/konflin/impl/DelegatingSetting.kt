package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.ConfigurationSource
import com.github.fkrull.konflin.Setting

internal class DelegatingSetting<out T>(
        override val name: String,
        private val configValueDelegate: (configSource: ConfigurationSource) -> T?,
        private val defaultValueDelegate: () -> T?
) : Setting<T> {
    override val default: T?
        get() = defaultValueDelegate()

    override fun get(configSource: ConfigurationSource): T? = configValueDelegate(configSource)
}
