package co.penguinLab.common

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}