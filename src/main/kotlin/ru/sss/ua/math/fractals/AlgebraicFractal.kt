package ru.sss.ua.math.fractals

import org.kotlinmath.Complex

interface AlgebraicFractal {
    fun isInSet(c: Complex): Float
}