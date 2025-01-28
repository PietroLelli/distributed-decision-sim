package simulation.action

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction
import simulation.DistributedDecisionEnvironment

class OrderGeneratorAction (val environment: DistributedDecisionEnvironment, node: Node<Any>, val resourceIds: List<String>, val numRecipesInOrder: Int, val numStepInRecipe: Int) : AbstractAction<Any>(node) {

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        environment.generateOrder(resourceIds, numRecipesInOrder, numStepInRecipe)
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}