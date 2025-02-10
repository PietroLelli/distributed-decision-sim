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
        environment.orders
            .asSequence()
            .filter { it.state != State.COMPLETE }
            .flatMap { it.recipes }
            .filter { it.state != State.COMPLETE }
            .flatMap { it.steps }
            .firstOrNull { s -> s.state == State.TOBEASSIGNED && prodUnitToAssign?.isCapableOfExecute(s) == true }
            ?.let { prodUnitToAssign?.addStepToWaitingList(it) }
    }

    private fun assignRandomStep() {
        val prodUnitToAssign = environment.nodes.mapNotNull { it.asProperty<Any, ProdUnit>() }
            .random()
        environment.orders
            .asSequence()
            .filter { it.state != State.COMPLETE }
            .flatMap { it.recipes }
            .filter { it.state != State.COMPLETE }
            .flatMap { it.steps }
            .firstOrNull { s -> s.state == State.TOBEASSIGNED && prodUnitToAssign.isCapableOfExecute(s) }
            ?.let { prodUnitToAssign.addStepToWaitingList(it) }

    }

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        when (routingPolicy) {
            "ShortWaitingList" -> assignStepToProdUnitWithShortWaitingList()
            "RandomProdUnit" -> assignRandomStep()
            else -> {
                assignNextStep()
            }
        }
        val prodUnits = environment.nodes.mapNotNull { it.asProperty<Any, ProdUnit>() }
        prodUnits.forEach {
            println()
            println("ProdUnit ${it.idCode} waitingList.size: ${it.waitingList.size}}")
        }
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}