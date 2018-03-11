package com.github.fkrull.konflin.converter

// Type parameter is not `out` for future extension of a `toConfig` method.
@Suppress("AddVarianceModifier")
internal interface Converter<OutType : Any, InType : Any> {
    val inType: ConfigType<InType>

    fun fromConfig(value: InType): OutType
}
