package com.github.fkrull.konflin.typedescriptors

import kotlin.reflect.KClass

internal interface TypeDescriptor<out Type : Any, SourceType : Any> {
    val configType: ConfigType<SourceType>
    val clazz: KClass<out Type>

    fun fromConfig(value: SourceType): Type
}
