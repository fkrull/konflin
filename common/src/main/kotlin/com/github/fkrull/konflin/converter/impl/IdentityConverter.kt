package com.github.fkrull.konflin.converter.impl

import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter
import kotlin.reflect.KClass

internal class IdentityConverter<Type : Any>(override val configType: ConfigType<Type>) : Converter<Type, Type> {
    override val clazz: KClass<out Type> = configType.clazz
    override fun fromConfig(value: Type): Type = value
}
