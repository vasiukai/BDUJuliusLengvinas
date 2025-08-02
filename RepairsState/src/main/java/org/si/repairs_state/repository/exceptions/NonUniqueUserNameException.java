package org.si.repairs_state.repository.exceptions;

public class NonUniqueUserNameException extends Exception {

    @Override
    public String getMessage(){

        return "Toks vartotojas jau yra";
    }
}
