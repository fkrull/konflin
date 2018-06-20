package konflin.impl

import konflin.Configuration
import konflin.ConfigurationSource
import konflin.MissingConfigValueException
import konflin.Setting

internal class DelegatingConfiguration(private val configSource: ConfigurationSource) : Configuration {
    override operator fun <T> get(setting: Setting<T>): T = getOrDefault(setting)
            ?: throw MissingConfigValueException("missing value for required setting '${setting.name}'")

    override operator fun <T> contains(setting: Setting<T>): Boolean = getOrDefault(setting) != null

    private fun <T> getOrDefault(setting: Setting<T>) =
            setting.get(configSource) ?: setting.default
}
