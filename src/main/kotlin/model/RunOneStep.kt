package model

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction

class RunOneStep(node: Node<Any>, val paramExample: Int): AbstractAction<Any>(node) {
    private val prodUnit: ProdUnit = node.asProperty()

    override fun cloneAction(node: Node<Any>?, reaction: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        prodUnit.executeFromWaitingList()
    }

    override fun getContext(): Context = Context.LOCAL
}