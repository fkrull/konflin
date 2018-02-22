package com.github.fkrull.konflin

import kotlin.test.*

class IntegrationTest {
    private val configSource = MockConfigurationSource()

    @Test
    fun should_define_and_get_String_setting() {
        val test = object: ConfigSpec {
            val name = setting<String>("test.name")
        }
        configSource.setString("test.name", "test name")
        val config = configuration(configSource)

        assertEquals("test name", config[test.name])
    }

    @Test
    fun should_define_and_get_Int_setting() {
        val test = object: ConfigSpec {
            val port = setting<Int>("test.port")
        }
        configSource.setInt("test.port", 80)
        val config = configuration(configSource)

        assertEquals(80, config[test.port])
    }

    @Test
    fun should_define_and_get_Boolean_setting() {
        val test = object: ConfigSpec {
            val enabled = setting<Boolean>("test.enabled")
        }
        configSource.setBoolean("test.enabled", true)
        val config = configuration(configSource)

        assertEquals(true, config[test.enabled])
    }

    @Test
    fun should_get_default_for_setting() {
        val test = object: ConfigSpec {
            val name = setting("test.name", "default name")
        }
        val config = configuration(configSource)

        assertEquals("default name", config[test.name])
    }

    @Test
    fun should_get_configured_value_instead_of_default() {
        val test = object: ConfigSpec {
            val name = setting("test.name", "default name")
        }
        configSource.setString("test.name", "configured name")
        val config = configuration(configSource)

        assertEquals("configured name", config[test.name])
    }

    @Test
    fun should_throw_exception_if_no_config_value_and_no_default() {
        val test = object: ConfigSpec {
            val name = setting<String>("test.name")
        }
        val config = configuration(configSource)

        assertFailsWith(MissingConfigValueException::class) {
            config[test.name]
        }
    }

    @Test
    fun contains_should_be_true_if_configured_value_is_present() {
        val test = object: ConfigSpec {
            val name = setting<String>("test.name")
        }
        configSource.setString("test.name", "configured name")
        val config = configuration(configSource)

        assertTrue(test.name in config)
    }

    @Test
    fun contains_should_be_true_if_no_configured_value_but_default_is_present() {
        val test = object: ConfigSpec {
            val name = setting("test.name", "default name")
        }
        val config = configuration(configSource)

        assertTrue(test.name in config)
    }

    @Test
    fun contains_should_be_false_if_no_configured_value_and_no_default() {
        val test = object: ConfigSpec {
            val name = setting<String>("test.name")
        }
        val config = configuration(configSource)

        assertFalse(test.name in config)
    }

    @Test
    fun should_throw_exception_when_trying_to_create_setting_with_unsupported_type() {
        assertFailsWith(UnsupportedConfigTypeException::class) {
            object: ConfigSpec {
                @Suppress("unused")
                val any = setting<Any>("test.any")
            }
        }
    }
}
