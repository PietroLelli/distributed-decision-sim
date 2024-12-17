package model

//object Step {
//    var state = StepState.TOBEASSIGNED
//
//    fun execute() = println("caio")
//}

import it.unibo.alchemist.model.*
import it.unibo.alchemist.model.reactions.Event
import org.danilopianini.util.ListSet

class Step (
    val idCode: String,
//    var type: StepType,
    var state: StepState = StepState.TOBEASSIGNED) {

    fun execute() = println("caio")
}

enum class StepState {
    TOBEASSIGNED,
    ASSIGNED,
    RUNNING,
    COMPLETE
}