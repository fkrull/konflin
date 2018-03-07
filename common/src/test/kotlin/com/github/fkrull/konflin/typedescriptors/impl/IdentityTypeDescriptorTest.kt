package com.github.fkrull.konflin.typedescriptors.impl

import com.github.fkrull.konflin.typedescriptors.ConfigType
import kotlin.test.Test
import kotlin.test.assertEquals

class IdentityTypeDescriptorTest {
    @Test
    fun should_set_type_from_ConfigType() {
        assertEquals(String::class, IdentityTypeDescriptor(ConfigType.Types.String).clazz)
        assertEquals(Boolean::class, IdentityTypeDescriptor(ConfigType.Types.Boolean).clazz)
        assertEquals(Int::class, IdentityTypeDescriptor(ConfigType.Types.Int).clazz)
    }

    @Test
    fun should_pass_through_argument_value_unchanged() {
        assertEquals("test value", IdentityTypeDescriptor(ConfigType.Types.String).fromConfig("test value"))
        assertEquals(12, IdentityTypeDescriptor(ConfigType.Types.Int).fromConfig(12))
        assertEquals(true, IdentityTypeDescriptor(ConfigType.Types.Boolean).fromConfig(true))
    }
}
