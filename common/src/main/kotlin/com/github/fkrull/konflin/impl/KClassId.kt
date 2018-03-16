package com.github.fkrull.konflin.impl

import kotlin.reflect.KClass

internal expect class KClassId(value: Any)

internal expect val <T : Any> KClass<T>.id: KClassId
