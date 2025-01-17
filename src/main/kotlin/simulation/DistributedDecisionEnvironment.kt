package simulation
import it.unibo.alchemist.model.Incarnation
import it.unibo.alchemist.model.environments.Continuous2DEnvironment
import it.unibo.alchemist.model.positions.Euclidean2DPosition
import simulation.model.Order
import simulation.model.Warehouse

class DistributedDecisionEnvironment<T>(incarnation: Incarnation<T, Euclidean2DPosition>): Continuous2DEnvironment<T>(incarnation) {
    val orders = mutableListOf<Order>()
    val completedOrders = mutableListOf<Order>()
    val warehouse : Warehouse = Warehouse("Warehouse1")
    var totCost : Double = 0.0
    var totTime : Double = 0.0
}