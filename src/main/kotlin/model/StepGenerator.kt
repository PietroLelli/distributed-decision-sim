package model

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction

class StepGenerator(node: Node<Any>) : AbstractAction<Any>(node) {

    val prodUnit: ProdUnit = node.asProperty()

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val step = Step("Step"+(0..100).random(), StepType("stepType"+(0..100).random(), 2.0, 1.0))
        print("Generated " + step.idCode +"\n")
        prodUnit.addStepToWaitingList(step)
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }

}