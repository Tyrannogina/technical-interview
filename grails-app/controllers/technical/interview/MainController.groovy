package technical.interview

import grails.converters.JSON

class MainController {

    def index() { }

    def graph(ValidateNumberCommand command) {
        if (!request.post) {
            return
        }

        if (!command.validate()) {
            render view: "/error"
            return
        }
        def calculations = new CalculationsService()
        def randList = calculations.calculationsSequence(command.firstNumber.toInteger(), command.secondNumber.toInteger())

        render view: 'graph', model: [typeList: randList]
    }
}

//Duplicated in src/main/groovy until I find a way of importing that one successfully
class ValidateNumberCommand {
    Integer firstNumber, secondNumber

    static constraints = {
        firstNumber blank: false, validator: { val ->
            if (!val || val < 0) {
                return 'basiccontroller.msg.invalid'
            }
        }
        secondNumber blank: false, validator: { val ->
            if (!val || val < 0) {
                return 'basiccontroller.msg.invalid'
            }
        }
    }
}
