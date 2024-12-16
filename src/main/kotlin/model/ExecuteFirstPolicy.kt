package model

class ExecuteFirstPolicy<T> : QueuingPolicy<T> {
    override fun getNext(list: List<T>): T {
        return list.first()
    }
}