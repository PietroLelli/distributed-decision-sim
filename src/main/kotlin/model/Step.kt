package model

import it.unibo.alchemist.model.*
import org.danilopianini.util.ListSet

class Step (val idCode: String, var type: StepType,
            override var actions: List<Action<Any>>,
            override var conditions: List<Condition<Any>>,
            override val inboundDependencies: ListSet<out Dependency>,
            override val inputContext: Context,
            override val node: Node<Any>,
            override val outboundDependencies: ListSet<out Dependency>,
            override val outputContext: Context,
            override val timeDistribution: TimeDistribution<Any>
) : Reaction<Any> {

    var state: StepState = StepState.TOBEASSIGNED

    override fun canExecute(): Boolean {
        return state != StepState.COMPLETE && state != StepState.RUNNING
    }

    override fun execute() {
        state = StepState.RUNNING
        println("Executing step $idCode")
        state = StepState.COMPLETE
    }

    override fun update(currentTime: Time, hasBeenExecuted: Boolean, environment: Environment<Any, *>) {
        TODO("Not yet implemented")
    }

    override fun initializationComplete(atTime: Time, environment: Environment<Any, *>) {
        TODO("Not yet implemented")
    }

    override fun compareTo(other: Actionable<Any>): Int {
        TODO("Not yet implemented")
    }

    override fun cloneOnNewNode(node: Node<Any>, currentTime: Time): Reaction<Any> {
        TODO("Not yet implemented")
    }


}

enum class StepState {
    TOBEASSIGNED,
    ASSIGNED,
    RUNNING,
    COMPLETE
}