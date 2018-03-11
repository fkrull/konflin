package com.github.fkrull.konflin.converter.impl

import com.github.fkrull.konflin.converter.ConfigType
import kotlin.test.Test
import kotlin.test.assertEquals

class IdentityConverterTest {
    @Test
    fun should_pass_through_argument_value_unchanged() {
        assertEquals("test value", IdentityConverter(ConfigType.Types.String).fromConfig("test value"))
        assertEquals(12, IdentityConverter(ConfigType.Types.Int).fromConfig(12))
        assertEquals(true, IdentityConverter(ConfigType.Types.Boolean).fromConfig(true))
    }
}
