package simulation.action

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction
import simulation.DistributedDecisionEnvironment

class OrderGeneratorAction (val environment: DistributedDecisionEnvironment, node: Node<Any>, val resourceIds: List<String>) : AbstractAction<Any>(node) {

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        //println("\nEXECUTION ORDER GENERATOR PROD-UNIT: " + node.id + "\n")
        val order = environment.generateOrder(resourceIds)
        environment.orders.add(order)
//        environment.orders.forEach(Order::printOrder)
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}