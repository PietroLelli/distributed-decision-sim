package model

interface QueuingPolicy<T> {
    fun getNext(list: List<T>): T
}
