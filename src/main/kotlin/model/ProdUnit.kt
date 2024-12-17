package model

import it.unibo.alchemist.model.*

class ProdUnit(val idCode: String, override val node: Node<Any>) : NodeProperty<Any> {
    val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(idCode, node)
    }

    var waitingList: List<Step> = listOf(Step("Step1"), Step("Step2"), Step("Step3"))

    fun addStepToWaitingList(step: Step) {
        if(step.state == StepState.TOBEASSIGNED) {
            waitingList = waitingList + step
            step.state = StepState.ASSIGNED
        }
    }

    fun executeFromWaitingList(){
        queuingPolicy.getNext(waitingList)
        println("ProdUnit: " + node.id + " executed step")
    }
}