package model

import it.unibo.alchemist.model.*

class ProdUnit(override val node: Node<Any>) : NodeProperty<Any> {
    val queuingPolicy: QueuingPolicy<StepExample> = ExecuteFirstPolicy()

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node)
    }

    //???
    var waitingList: List<StepExample> = listOf(StepExample("1"), StepExample("2"), StepExample("3"))

//    fun addActivityToWaitingList(step: Step) {
//        if(step.state == StepState.TOBEASSIGNED) {
//            waitingList = waitingList + step
//            step.state = StepState.ASSIGNED
//        }
//    }

    fun addActivityToWaitingList() {
        val step = StepExample("1")
        waitingList = waitingList + step

    }

    fun executeFromWaitingList(){
        queuingPolicy.getNext(waitingList).execute()
        println("Executing first reaction prodUnit")
    }
}