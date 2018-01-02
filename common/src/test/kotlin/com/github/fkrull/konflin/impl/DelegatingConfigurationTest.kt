package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.*
import kotlin.test.*

class DelegatingConfigurationTest {
    private val configurationSource = mockk<ConfigurationSource>()
    private val configuration = DelegatingConfiguration(configurationSource)

    @Test
    fun `get should return value from Setting`() {
        val setting = givenSettingWith(15, null)

        assertEquals(15, configuration[setting])
    }

    @Test
    fun `get should return default value from Setting if no value from config`() {
        val setting = givenSettingWith(null, 15)

        assertEquals(15, configuration[setting])
    }

    @Test
    fun `get should throw exception if no config value and no default`() {
        val setting = givenSettingWith(null, null)

        assertFailsWith(MissingConfigValueException::class) {
            configuration[setting]
        }
    }

    @Test
    fun `contains should return true if Setting returns a value`() {
        val setting = givenSettingWith(15, null)

        assertTrue(setting in configuration)
    }

    @Test
    fun `contains should return true if Setting has no value but a default`() {
        val setting = givenSettingWith(null, 15)

        assertTrue(setting in configuration)
    }

    @Test
    fun `contains should return false if Setting has no value and no default`() {
        val setting = givenSettingWith(null, null)

        assertFalse(setting in configuration)
    }

    private fun <T> givenSettingWith(value: T?, default: T?): Setting<*> {
        val setting = mockk<Setting<T>>()
        every { setting.name } returns "test setting"
        every { setting.get(configurationSource) } returns value
        every { setting.default } returns default
        return setting
    }
}
