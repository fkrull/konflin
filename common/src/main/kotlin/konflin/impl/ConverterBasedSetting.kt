package konflin.impl

import konflin.ConfigurationSource
import konflin.Setting
import konflin.converter.ConfigType
import konflin.converter.Converter

internal class ConverterBasedSetting<out T : Any>(
    override val name: String,
    override val default: T?,
    private val converter: Converter<T, Any>
) : Setting<T> {
    override fun get(configSource: ConfigurationSource): T? = when (converter.inType) {
        ConfigType.Types.String -> configSource.getString(name)?.let { converter.fromConfig(it) }
        ConfigType.Types.Int -> configSource.getInt(name)?.let { converter.fromConfig(it) }
        ConfigType.Types.Boolean -> configSource.getBoolean(name)?.let { converter.fromConfig(it) }
    }
}
