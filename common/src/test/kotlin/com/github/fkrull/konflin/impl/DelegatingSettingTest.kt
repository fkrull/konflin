package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.MockConfigurationSource
import kotlin.test.Test
import kotlin.test.assertEquals

class DelegatingSettingTest {
    private val configurationSource = MockConfigurationSource()

    @Test
    fun should_get_default_value_from_getter_function() {
        val setting = DelegatingSetting("name", { null }, { 15 })

        assertEquals(15, setting.default)
    }

    @Test
    fun should_get_config_value_from_getter_function() {
        val setting = DelegatingSetting("name", { if (it == configurationSource) 15 else null }, { null })

        assertEquals(15, setting.get(configurationSource))
    }
}
