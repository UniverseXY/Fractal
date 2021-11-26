package ru.sss.ua

import CartesianPlane
import ru.sss.ua.math.mod2
import org.kotlinmath.DefaultComplex as Complex



fun main(args: Array<String>) {
    var z1 = Complex(3.0,3.0)
    var z = z1.mod2
    println(z1)

}

