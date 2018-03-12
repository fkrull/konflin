package com.github.fkrull.konflin.converter

import kotlin.reflect.KClass

// Type parameter is not `out` for future extension of a `toConfig` method.
@Suppress("AddVarianceModifier")
internal interface Converter<OutType : Any, InType : Any> {
    val inType: ConfigType<InType>
    val outType: KClass<out OutType>

    fun fromConfig(value: InType): OutType
}
