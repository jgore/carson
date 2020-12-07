package pl.goreit.carson.api;

public class DomainException extends Exception {

    public DomainException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
    }
}
