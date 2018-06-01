package org.si.repairs_state.repository.exceptions;

public class EmptyFieldException extends Exception{

    @Override
    public String getMessage(){

        return "Užpildykite visus laukus";
    }
}
