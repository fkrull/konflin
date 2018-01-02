package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.ConfigurationSource
import com.github.fkrull.konflin.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class DelegatingSettingTest {
    private val configurationSource = mockk<ConfigurationSource>()

    @Test
    fun `should get default value from getter function`() {
        val setting = DelegatingSetting("name", { null }, { 15 })

        assertEquals(15, setting.default)
    }

    @Test
    fun `should get config value from getter function`() {
        val setting = DelegatingSetting("name", { if (it == configurationSource) 15 else null }, { null })

        assertEquals(15, setting.get(configurationSource))
    }
}
