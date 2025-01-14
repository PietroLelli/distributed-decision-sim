package model

//class StepType (val idCode: String, var time : Double, var cost: Double)
enum class StepType(val idCode: String, var time: Double, var cost: Double) {
    TYPE_S("SIMPLE", 1.0, 10.0),
    TYPE_C("COMPLEX", 3.0, 30.0);
}
