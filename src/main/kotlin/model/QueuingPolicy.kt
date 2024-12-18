package model

interface QueuingPolicy<T> {
    fun getNext(list: List<T>, condition: (T) -> Boolean = { true }): T?
}
