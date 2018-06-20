package konflin

import konflin.converter.ConfigType
import konflin.converter.Converter
import kotlin.test.*

private data class TestType1(val value: String)
private object TestConverter1 : Converter<TestType1, String> {
    override val inType = ConfigType.Types.String
    override val outType = TestType1::class
    override fun fromConfig(value: String) = TestType1(value)
}

private data class TestType2(val value: String)
private object TestConverter2 : Converter<TestType2, String> {
    override val inType = ConfigType.Types.String
    override val outType = TestType2::class
    override fun fromConfig(value: String) = TestType2(value)
}

class ConfigSpecTest {
    private val configSpec = object : ConfigSpec(TestConverter1, TestConverter2) {}
    private val configSource = MockConfigurationSource()

    @Test
    fun should_create_Setting_for_String() {
        configSource.setString("test.name", "test value")

        val setting = configSpec.setting("test.name", "test default")

        assertEquals("test.name", setting.name)
        assertEquals("test default", setting.default)
        assertEquals("test value", setting.get(configSource))
    }

    @Test
    fun should_create_Setting_for_Int() {
        configSource.setInt("test.name", 8080)

        val setting = configSpec.setting("test.name", 80)

        assertEquals("test.name", setting.name)
        assertEquals(80, setting.default)
        assertEquals(8080, setting.get(configSource))
    }

    @Test
    fun should_create_Setting_for_Boolean() {
        configSource.setBoolean("test.name", true)

        val setting = configSpec.setting("test.name", true)

        assertEquals("test.name", setting.name)
        assertEquals(true, setting.default)
        assertEquals(true, setting.get(configSource))
    }

    @Test
    fun should_set_null_for_default_if_not_specified() {
        val setting = configSpec.setting<String>("test.name")

        assertNull(setting.default)
    }

    @Test
    fun should_throw_exception_for_unsupported_type() {
        assertFailsWith(UnsupportedConfigTypeException::class) {
            configSpec.setting<Throwable>("test.name")
        }
    }

    @Test
    fun should_create_Setting_for_first_custom_Converter() {
        configSource.setString("test.name", "configured name")

        val setting = configSpec.setting<TestType1>("test.name")

        assertEquals("test.name", setting.name)
        assertEquals(TestType1("configured name"), setting.get(configSource))
    }

    @Test
    fun should_create_Setting_for_second_custom_Converter() {
        configSource.setString("test.name", "configured name")

        val setting = configSpec.setting<TestType2>("test.name")

        assertEquals("test.name", setting.name)
        assertEquals(TestType2("configured name"), setting.get(configSource))
    }
}
