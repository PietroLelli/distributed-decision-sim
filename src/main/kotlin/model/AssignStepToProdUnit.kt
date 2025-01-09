package model

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction

class AssignStepToProdUnit (node: Node<Any>, val environment: DistributedDecisionEnvironment<Any>) : AbstractAction<Any>(node) {
    private val prodUnit: ProdUnit = node.asProperty()

    private fun getNextStepToAssign(): Step? {
        val order = environment.orders.firstOrNull { order -> order.state != State.COMPLETE }
        val recipe = order?.recipes?.firstOrNull { recipe -> recipe.state != State.COMPLETE }
        val step = recipe?.steps?.firstOrNull { step -> step.state == State.TOBEASSIGNED }

        if (step != null) {
            println("StepToAssign: "+step.idCode)
        }
        return step
    }

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        getNextStepToAssign()?.let { prodUnit.addStepToWaitingList(it) }
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}