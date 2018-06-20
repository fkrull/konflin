package konflin

import kotlin.test.*

private object TestConfigSpec : ConfigSpec() {
    val setting1 = setting<String>("setting1")
    val setting2 = setting<String>("setting2")
}

class TestConfigurationTest {
    private val testConfiguration = TestConfiguration(TestConfigSpec.setting1 to "value1")

    @Test
    fun should_return_given_value_for_setting() {
        assertEquals("value1", testConfiguration[TestConfigSpec.setting1])
    }

    @Test
    fun should_throw_exception_if_value_for_setting_was_not_specified() {
        assertFailsWith(MissingConfigValueException::class) {
            testConfiguration[TestConfigSpec.setting2]
        }
    }

    @Test
    fun contains_should_return_true_if_setting_was_specified() {
        assertTrue(TestConfigSpec.setting1 in testConfiguration)
    }

    @Test
    fun contains_should_return_false_if_setting_was_not_specified() {
        assertFalse(TestConfigSpec.setting2 in testConfiguration)
    }
}
