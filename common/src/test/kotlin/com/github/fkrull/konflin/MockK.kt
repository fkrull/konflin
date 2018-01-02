package com.github.fkrull.konflin

import io.mockk.MockKMatcherScope
import io.mockk.MockKStubScope

expect inline fun <reified T : Any> mockk(): T
expect inline fun <T> every(noinline stubBlock: MockKMatcherScope.() -> T): MockKStubScope<T>
