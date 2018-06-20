package konflin.converter.impl

import konflin.converter.ConfigType
import kotlin.test.Test
import kotlin.test.assertEquals

class IdentityConverterTest {
    @Test
    fun should_set_type_from_ConfigType() {
        assertEquals(String::class, IdentityConverter(ConfigType.Types.String).outType)
        assertEquals(Boolean::class, IdentityConverter(ConfigType.Types.Boolean).outType)
        assertEquals(Int::class, IdentityConverter(ConfigType.Types.Int).outType)
    }

    @Test
    fun should_pass_through_argument_value_unchanged() {
        assertEquals("test value", IdentityConverter(ConfigType.Types.String).fromConfig("test value"))
        assertEquals(12, IdentityConverter(ConfigType.Types.Int).fromConfig(12))
        assertEquals(true, IdentityConverter(ConfigType.Types.Boolean).fromConfig(true))
    }
}
