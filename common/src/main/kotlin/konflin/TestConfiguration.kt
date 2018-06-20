package konflin

/**
 * A [Configuration] implementation that simply fixed values for given [Setting] objects.
 *
 * This class is intended for unit tests. Note that this class does not use default values, meaning you must explicitly
 * specify any value you need.
 *
 * @param settings a list of ([Setting], value) pairs
 */
class TestConfiguration(vararg settings: Pair<Setting<*>, *>) : Configuration {
    private val config: Map<String, Any?> = settings
        .map { setting -> setting.first.name to setting.second }
        .toMap()

    override fun <T> get(setting: Setting<T>): T = getValue(setting)
        ?: throw MissingConfigValueException("missing setting")

    override fun <T> contains(setting: Setting<T>): Boolean = getValue(setting) != null

    @Suppress("UNCHECKED_CAST")
    private fun <T> getValue(setting: Setting<T>) = config[setting.name] as T?
}
