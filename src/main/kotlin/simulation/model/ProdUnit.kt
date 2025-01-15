package simulation.model

import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.NodeProperty
import simulation.policy.ExecuteFirstPolicy
import simulation.policy.QueuingPolicy

class ProdUnit(override val node: Node<Any>, private val capabilities: List<String>) : NodeProperty<Any> {

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node, capabilities)
    }

    private var waitingList: List<Step> = listOf()
    private val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()

    fun isCapableOfExecute(step: Step): Boolean {
        return when {
            "ALL" in capabilities -> true
            "A" in capabilities && step.type == StepType.TYPE_A -> true
            "B" in capabilities && step.type == StepType.TYPE_B -> true
            "C" in capabilities && step.type == StepType.TYPE_C -> true
            else -> false
        }
    }

    fun addStepToWaitingList(step: Step) {
        if(step.state == State.TOBEASSIGNED) {
            waitingList = waitingList + step
            step.state = State.ASSIGNED
        }
    }

    fun executeFromWaitingList() {
        val step = queuingPolicy.getNext(waitingList) { step -> step.state == State.ASSIGNED }
        if (step != null) {
            step.execute()
            waitingList = waitingList.filter { it.idCode != step.idCode }
        }
    }
}