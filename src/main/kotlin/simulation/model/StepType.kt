package simulation.model

enum class StepType(val idCode: String, var time: Double, var cost: Double) {
    TYPE_A("A", 1.0, 10.0),
    TYPE_B("B", 2.0, 20.0),
    TYPE_C("C", 3.0, 30.0),
}
