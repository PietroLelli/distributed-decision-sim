package model

import it.unibo.alchemist.model.*
import it.unibo.alchemist.model.reactions.Event
import org.danilopianini.util.ListSet
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class Step (
    val idCode: String,
    var type: StepType,
    var state: StepState = StepState.TOBEASSIGNED) {

    fun execute() {
        state = StepState.RUNNING
        //Thread.sleep(type.time.toDuration(DurationUnit.SECONDS).inWholeMilliseconds)
        state = StepState.COMPLETE
    }
}

enum class StepState {
    TOBEASSIGNED,
    ASSIGNED,
    RUNNING,
    COMPLETE
}