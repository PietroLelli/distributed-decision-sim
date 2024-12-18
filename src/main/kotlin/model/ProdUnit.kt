package model

import it.unibo.alchemist.model.*

class ProdUnit(override val node: Node<Any>) : NodeProperty<Any> {

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node)
    }

    var waitingList: List<Step> = listOf()
    val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()

    fun addStepToWaitingList(step: Step) {
        if(step.state == StepState.TOBEASSIGNED) {
            waitingList = waitingList + step
            step.state = StepState.ASSIGNED
        }
    }

    fun executeFromWaitingList() {
        val step = queuingPolicy.getNext(waitingList) { step -> step.state == StepState.ASSIGNED }
        if (step != null) {
            step.execute()
            waitingList = waitingList.filter { it.idCode != step.idCode }
            println("ProdUnit: " + node.id + " executed step: " + step.idCode)
        } else {
            println("ProdUnit: " + node.id + " has no steps to execute")
        }
    }
}