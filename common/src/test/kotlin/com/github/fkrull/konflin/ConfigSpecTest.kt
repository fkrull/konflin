package com.github.fkrull.konflin

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ConfigSpecTest {
    private val configSpec = object : ConfigSpec {}
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
    fun should_throw_exception_for_unsupported_type() {
        assertFailsWith(UnsupportedConfigTypeException::class) {
            configSpec.setting<Throwable>("test.name")
        }
    }
}
