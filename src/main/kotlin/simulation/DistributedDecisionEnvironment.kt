package simulation
import it.unibo.alchemist.model.*
import it.unibo.alchemist.model.environments.Continuous2DEnvironment
import it.unibo.alchemist.model.positions.Euclidean2DPosition
import simulation.model.Order
import simulation.model.Result

class DistributedDecisionEnvironment<T>(incarnation: Incarnation<T, Euclidean2DPosition>): Continuous2DEnvironment<T>(incarnation) {
    val orders = mutableListOf<Order>()
    val results = mutableListOf<Result>()
    val completedOrders = mutableListOf<Order>()
    var totCost : Double = 0.0
    var totTime : Double = 0.0

    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun addResult(result: Result) {
        results.add(result)
    }
}