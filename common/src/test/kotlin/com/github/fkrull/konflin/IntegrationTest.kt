package com.github.fkrull.konflin

import kotlin.test.Test
import kotlin.test.assertEquals

class IntegrationTest {
    private val configSource = MockConfigurationSource()

    @Test
    fun should_define_config_spec_and_get_values_from_config_source() {
        val test = object: ConfigSpec {
            val name = setting<String>("test.name")
            val port = setting("test.port", 8080)
        }
        configSource.setString("test.name", "test name")
        val config = configuration(configSource)

        assertEquals("test name", config[test.name])
        assertEquals(8080, config[test.port])
    }
}
