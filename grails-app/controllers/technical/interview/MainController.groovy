package technical.interview

import grails.converters.JSON

class MainController {

    def index() { }

    def graph(ValidateNumberCommand command) {
        if (!request.post) {
            return
        }

        if (!command.validate()) {
            return [success: false, messages: command.errors.allErrors] as JSON
        }
        def calculations = new CalculationsService()
        calculations.calculationsSequence(command.firstNumber.toInteger(), command.secondNumber.toInteger())
       // servicioUno.crearCalculations(command.numero)

        //def calculations = new Calculations(firstNumber: params.firstNumber.toInteger(), secondNumber: params.secondNumber.toInteger()[])

        /*def owners = Owner.findAllByLastName(params.lastName)
        if (!owners) {
            return [message: 'owners.not.found']
        }

        if (owners.size() > 1) {
            render view: 'selection', model: [owners: owners]
        }
        else {
            redirect action: 'show', id: owners[0].id
        }
        */
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
