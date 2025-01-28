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

class AssignStepToProdUnitAction (node: Node<Any>, val environment: DistributedDecisionEnvironment, val routingPolicy: String) : AbstractAction<Any>(node) {
    private val prodUnit: ProdUnit = node.asProperty()

    private fun assignNextStep() {
        //environment.nodes.mapNotNull { it.asProperty<T, ProdUnit>() }.first()
        environment.orders
            .asSequence()
            .filter { it.state != State.COMPLETE }
            .flatMap { it.recipes }
            .filter { it.state != State.COMPLETE }
            .flatMap { it.steps }
            .firstOrNull { s -> prodUnit.isCapableOfExecute(s) && s.state == State.TOBEASSIGNED }
            ?.let { prodUnit.addStepToWaitingList(it) }
    }

    private fun assignStepToProdUnitWithShortWaitingList() {
        val prodUnitToAssign = environment.nodes.mapNotNull { it.asProperty<Any, ProdUnit>() }
            .minByOrNull { it.waitingList.size }
         val step = environment.orders
            .asSequence()
            .filter { it.state != State.COMPLETE }
            .flatMap { it.recipes }
            .filter { it.state != State.COMPLETE }
            .flatMap { it.steps }
            .firstOrNull { s -> s.state == State.TOBEASSIGNED && prodUnitToAssign?.isCapableOfExecute(s) == true }
//            ?.let { prodUnitToAssign?.addStepToWaitingList(it) }
        environment.nodes.mapNotNull { it.asProperty<Any, ProdUnit>() }.forEach { println("ProdUnit ${it.idCode} waiting list size: ${it.waitingList.size}") }
        if (step != null) {
            prodUnitToAssign?.addStepToWaitingList(step)
        }
        println("ProdUnit ${prodUnit.idCode} assigned step ${step?.idCode} to ProdUnit ${prodUnitToAssign?.idCode}")
    }

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        if (routingPolicy == "ShortWaitingList")
            assignStepToProdUnitWithShortWaitingList()
        else
            assignNextStep()
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}