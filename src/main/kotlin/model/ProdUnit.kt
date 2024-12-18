package model

import it.unibo.alchemist.model.*

class ProdUnit(override val node: Node<Any>) : NodeProperty<Any> {
    val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node)
    }

    var waitingList: List<Step> = listOf()

    fun addStepToWaitingList(step: Step) {
        if(step.state == StepState.TOBEASSIGNED) {
            waitingList = waitingList + step
            step.state = StepState.ASSIGNED
        }
    }

    fun executeFromWaitingList() {
        if (waitingList.isEmpty())
            return

        val step = queuingPolicy.getNext(waitingList)
        step.execute()
        waitingList = waitingList.filter { it.idCode != step.idCode }
        println("ProdUnit: " + node.id + " executed step: " + step.idCode)
    }
}