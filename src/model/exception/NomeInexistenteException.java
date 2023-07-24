package model.exception;

public class NomeInexistenteException extends RuntimeException {
    public NomeInexistenteException(String mensagem) {
        super(mensagem);
    }
}
