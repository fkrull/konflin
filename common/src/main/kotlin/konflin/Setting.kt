package konflin

interface Setting<out T> {
    val name: String

    /**
     * @suppress
     */
    val default: T?

    /**
     * @suppress
     */
    fun get(configSource: ConfigurationSource): T?
}
