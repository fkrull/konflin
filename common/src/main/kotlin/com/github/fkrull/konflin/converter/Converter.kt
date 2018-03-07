package com.github.fkrull.konflin.converter

import kotlin.reflect.KClass

internal interface Converter<out Type : Any, SourceType : Any> {
    val configType: ConfigType<SourceType>
    val clazz: KClass<out Type>

    fun fromConfig(value: SourceType): Type
}
