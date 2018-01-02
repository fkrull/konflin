package com.github.fkrull.konflin

import kotlin.test.Test
import kotlin.test.assertEquals

class IntegrationTest {
    private val configSource = mockk<ConfigurationSource>()

    @Test
    fun `should define config spec and get values from config source`() {
        val test = object: ConfigSpec {
            val name = setting<String>("test.name")
            val port = setting("test.port", 8080)
        }
        every { configSource.getString("test.name") } returns "test name"
        every { configSource.getInt("test.port") } returns null
        val config = configuration(configSource)

        assertEquals("test name", config[test.name])
        assertEquals(8080, config[test.port])
    }
}
