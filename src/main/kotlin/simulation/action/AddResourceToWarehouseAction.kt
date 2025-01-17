package simulation.action

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction
import simulation.DistributedDecisionEnvironment
import simulation.model.Resource

class AddResourceToWarehouseAction (node: Node<Any>, val environment: DistributedDecisionEnvironment<Any>, val resourceIds: List<String>) : AbstractAction<Any>(node) {

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val randomResource = Resource(resourceIds.random())
        val randomQuantity = (1..5).random()
        environment.warehouse.addResource(randomResource.idCode, randomQuantity)
        println("Added $randomQuantity of ${randomResource.idCode} to warehouse")
        environment.warehouse.printResources()
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}