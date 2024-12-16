package model

class Recipe (val idCode: String, var steps: List<Step>, var result: Result) {
    fun addStep(step: Step) {
        steps += step
    }

    fun removeStep(step: Step) {
        steps -= step
    }
}