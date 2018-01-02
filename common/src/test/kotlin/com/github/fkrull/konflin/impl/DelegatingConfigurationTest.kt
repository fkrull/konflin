package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.*
import kotlin.test.*

class DelegatingConfigurationTest {
    private val configurationSource = mockk<ConfigurationSource>()
    private val configuration = DelegatingConfiguration(configurationSource)

    @Test
    fun get_should_return_value_from_Setting() {
        val setting = givenSettingWith(15, null)

        assertEquals(15, configuration[setting])
    }

    @Test
    fun get_should_return_default_value_from_Setting_if_no_value_from_config() {
        val setting = givenSettingWith(null, 15)

        assertEquals(15, configuration[setting])
    }

    @Test
    fun get_should_throw_exception_if_no_config_value_and_no_default() {
        val setting = givenSettingWith(null, null)

        assertFailsWith(MissingConfigValueException::class) {
            configuration[setting]
        }
    }

    @Test
    fun contains_should_return_true_if_Setting_returns_a_value() {
        val setting = givenSettingWith(15, null)

        assertTrue(setting in configuration)
    }

    @Test
    fun contains_should_return_true_if_Setting_has_no_value_but_a_default() {
        val setting = givenSettingWith(null, 15)

        assertTrue(setting in configuration)
    }

    @Test
    fun contains_should_return_false_if_Setting_has_no_value_and_no_default() {
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
