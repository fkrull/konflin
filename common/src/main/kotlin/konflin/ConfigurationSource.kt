package konflin

interface ConfigurationSource {
    fun getString(name: String): String?
    fun getInt(name: String): Int?
    fun getBoolean(name: String): Boolean?
}
