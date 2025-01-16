package simulation.action

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction
import simulation.DistributedDecisionEnvironment
import simulation.model.ProdUnit
import simulation.model.Resource
import simulation.model.State
import simulation.model.Step

class AddResourceToWarehouseAction (node: Node<Any>, val environment: DistributedDecisionEnvironment<Any>) : AbstractAction<Any>(node) {

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val randomResource = listOf(Resource("Resource1"), Resource("Resource2"), Resource("Resource3")).random()
        val randomQuantity = (1..5).random()
        val matchingResource = environment.warehouse.resources.keys.find { it.idCode == randomResource.idCode }

        if (matchingResource != null) {
            val oldQuantity = environment.warehouse.resources[matchingResource]!!
            environment.warehouse.resources[matchingResource] = oldQuantity + randomQuantity
        } else {
            environment.warehouse.resources += Pair(randomResource, randomQuantity)
        }
        //println("Added $randomQuantity of ${randomResource.idCode} to warehouse")
        environment.warehouse.printResources()
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}