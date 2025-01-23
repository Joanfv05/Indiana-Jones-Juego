package model.exception;

public class MaximoIntentosAlcanzadosException extends Exception{

    public MaximoIntentosAlcanzadosException() {
        super();
    }

    public MaximoIntentosAlcanzadosException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        System.out.println("Se ha alcanzado el número máximo de intentos. Adiós");
    }
}

