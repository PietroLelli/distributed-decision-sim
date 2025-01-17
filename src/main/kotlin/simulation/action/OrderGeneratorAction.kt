package simulation.action

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction
import simulation.DistributedDecisionEnvironment
import simulation.model.Order
import simulation.generator.OrderGenerator

class OrderGeneratorAction (node: Node<Any>, val environment: DistributedDecisionEnvironment<Any>, useResource: String) : AbstractAction<Any>(node) {

    private val useResourceBoolean: Boolean = useResource == "true"
    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        //println("\nEXECUTION ORDER GENERATOR PROD-UNIT: " + node.id + "\n")
        val order = OrderGenerator.generateOrder(environment, useResourceBoolean)
        environment.addOrder(order)
//        environment.orders.forEach(Order::printOrder)
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}