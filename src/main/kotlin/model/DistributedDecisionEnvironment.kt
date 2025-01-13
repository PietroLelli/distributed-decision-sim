package model
import it.unibo.alchemist.model.*
import it.unibo.alchemist.model.environments.Continuous2DEnvironment
import it.unibo.alchemist.model.positions.Euclidean2DPosition

class DistributedDecisionEnvironment<T>(incarnation: Incarnation<T, Euclidean2DPosition>): Continuous2DEnvironment<T>(incarnation) {
    val orders = mutableListOf<Order>()
    val results = mutableListOf<Result>()

    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun addResult(result: Result) {
        results.add(result)
    }
}