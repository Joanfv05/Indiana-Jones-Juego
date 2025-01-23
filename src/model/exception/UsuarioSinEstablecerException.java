package model.exception;

public class UsuarioSinEstablecerException extends Exception{
    public UsuarioSinEstablecerException (String s){
        super(s);
    }

    public UsuarioSinEstablecerException (){
        super();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        System.out.println("Error: No se ha establecido un usuario.");
    }
}

