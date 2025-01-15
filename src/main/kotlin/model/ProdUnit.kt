package model

import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.NodeProperty

class ProdUnit(override val node: Node<Any>, val capabilities: List<String>) : NodeProperty<Any> {

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node, capabilities)
    }

    private var waitingList: List<Step> = listOf()
    private val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()

    fun isCapableOfExecute(step: Step): Boolean {
        println("Capabilities: ${capabilities.size}")
        if(capabilities.contains("ALL")) {
            return true
        } else if (capabilities.contains("A") && step.type == StepType.TYPE_A) {
            return true
        } else if (capabilities.contains("B") && step.type == StepType.TYPE_B) {
            return true
        } else if (capabilities.contains("C") && step.type == StepType.TYPE_C) {
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