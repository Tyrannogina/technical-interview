class ValidateNumberCommand {
    Integer firstNumber, secondNumber

    static constraints = {
        firstNumber (blank: false, validator: isValid())
        secondNumber (blank: false, validator: isValid())
    }

    static isValid(Integer number){
        if (!number || number < 0) {
            'basiccontroller.msg.invalid'
        }
    }
}
