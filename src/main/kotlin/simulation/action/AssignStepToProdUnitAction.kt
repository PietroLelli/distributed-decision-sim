package simulation.action

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction
import simulation.DistributedDecisionEnvironment
import simulation.model.ProdUnit
import simulation.model.State
import simulation.model.Step

class AssignStepToProdUnitAction (node: Node<Any>, val environment: DistributedDecisionEnvironment<Any>) : AbstractAction<Any>(node) {
    private val prodUnit: ProdUnit = node.asProperty()

    private fun getNextStepToAssign(): Step? {
        val step = environment.orders
            .asSequence()
            .filter { it.state != State.COMPLETE }
            .flatMap { it.recipes }
            .filter { it.state != State.COMPLETE }
            .flatMap { it.steps }
            .firstOrNull { s -> prodUnit.isCapableOfExecute(s) && s.state == State.TOBEASSIGNED }
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