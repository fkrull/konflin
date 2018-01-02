package com.github.fkrull.konflin

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ConfigSpecTest {
    private val configSpec = object : ConfigSpec {}
    private val configSource = mockk<ConfigurationSource>()

    @Test
    fun should_create_Setting_for_String() {
        every { configSource.getString("test.name") } returns "test value"

        val setting = configSpec.setting("test.name", "test default")

        assertEquals("test.name", setting.name)
        assertEquals("test default", setting.default)
        assertEquals("test value", setting.get(configSource))
    }

    @Test
    fun should_create_Setting_for_Int() {
        every { configSource.getInt("test.name") } returns 8080

        val setting = configSpec.setting("test.name", 80)

        assertEquals("test.name", setting.name)
        assertEquals(80, setting.default)
        assertEquals(8080, setting.get(configSource))
    }

    @Test
    fun should_create_Setting_for_Boolean() {
        every { configSource.getBoolean("test.name") } returns true

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
