package model

class StepExample (val idCode: String){
    var state: StepExampleState = StepExampleState.TOBEASSIGNED

   fun execute() {
       state = StepExampleState.RUNNING
       println("Executing step $idCode")
       state = StepExampleState.COMPLETE
   }
}

enum class StepExampleState {
    TOBEASSIGNED,
    ASSIGNED,
    RUNNING,
    COMPLETE
}
