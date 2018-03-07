package com.github.fkrull.konflin.impl

import com.github.fkrull.konflin.MockConfigurationSource
import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter
import kotlin.reflect.KClass
import kotlin.test.Test
import kotlin.test.assertEquals

private class MockConverter<SourceType : Any>(
    val expectedInput: SourceType,
    val output: String,
    override val configType: ConfigType<SourceType>
) : Converter<String, SourceType> {
    override val clazz: KClass<String> = String::class

    override fun fromConfig(value: SourceType): String =
        if (value == expectedInput) output else "argument '$value' != expected value '$expectedInput'"
}

class ConverterBasedSettingTest {
    private val configSource = MockConfigurationSource()
    private val stringConverter: Converter<String, Any> = MockConverter("test value", "converted value", ConfigType.Types.String)
    private val intConverter: Converter<String, Any> = MockConverter(99, "converted value", ConfigType.Types.Int)
    private val booleanConverter: Converter<String, Any> = MockConverter(true, "converted value", ConfigType.Types.Boolean)

    init {
        configSource.setString("test.setting", "test value")
        configSource.setInt("test.setting", 99)
        configSource.setBoolean("test.setting", true)
    }

    @Test
    fun should_call_getString_and_convert_value_if_converter_has_ConfigType_String() {
        val result = ConverterBasedSetting("test.setting", null, stringConverter).get(configSource)

        assertEquals("converted value", result)
    }

    @Test
    fun should_call_getInt_and_convert_value_if_converter_has_ConfigType_Int() {
        val result = ConverterBasedSetting("test.setting", null, intConverter).get(configSource)

        assertEquals("converted value", result)
    }

    @Test
    fun should_call_getBoolean_and_convert_value_if_converter_has_ConfigType_Boolean() {
        val result = ConverterBasedSetting("test.setting", null, booleanConverter).get(configSource)

        assertEquals("converted value", result)
    }
}
