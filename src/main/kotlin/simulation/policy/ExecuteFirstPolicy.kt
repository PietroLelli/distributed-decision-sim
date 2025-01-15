package simulation.policy

class ExecuteFirstPolicy<T> : QueuingPolicy<T> {
    override fun getNext(list: List<T>, condition: (T) -> Boolean): T? {
        return list.firstOrNull(condition)
    }
}