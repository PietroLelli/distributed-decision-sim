package model
import it.unibo.alchemist.model.*
import it.unibo.alchemist.model.environments.Continuous2DEnvironment
import it.unibo.alchemist.model.positions.Euclidean2DPosition

class DistributedDecisionEnvironment<T>(incarnation: Incarnation<T, Euclidean2DPosition>): Continuous2DEnvironment<T>(incarnation) {
    val orders = mutableListOf<Order>()

    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun getOrder(): Order {
        return orders.removeAt(0)
    }
}