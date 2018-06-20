package konflin

class MockConfigurationSource : ConfigurationSource {
    private val strings = HashMap<String, String>()
    private val ints = HashMap<String, Int>()
    private val bools = HashMap<String, Boolean>()

    override fun getString(name: String) = strings[name]
    override fun getInt(name: String) = ints[name]
    override fun getBoolean(name: String) = bools[name]

    fun setString(name: String, value: String) {
        strings[name] = value
    }

    fun setInt(name: String, value: Int) {
        ints[name] = value
    }

    fun setBoolean(name: String, value: Boolean) {
        bools[name] = value
    }
}
