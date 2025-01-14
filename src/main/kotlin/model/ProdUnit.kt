package model

import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.NodeProperty

class ProdUnit(override val node: Node<Any>, val capabilities: String) : NodeProperty<Any> {

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node, capabilities)
    }

    private var waitingList: List<Step> = listOf()
    private val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()

    fun isCapableOfExecute(step: Step): Boolean {
        if(capabilities == "ALL") {
            return true
        } else if (capabilities == "COMPLEX" && step.type == StepType.TYPE_C) {
            return true
        } else if (capabilities == "SIMPLE" && step.type == StepType.TYPE_S) {
            return true
        }
        return false
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
            //println("ProdUnit: " + node.id + " executed step: " + step.idCode)
        }
//        else {
//            println("ProdUnit: " + node.id + " has no steps to execute")
//        }
    }
}