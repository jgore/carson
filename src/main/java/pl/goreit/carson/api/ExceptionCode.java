package pl.goreit.carson.api;

public enum ExceptionCode {

    CAR_NOT_EXIST("CAR_NOT_EXIST", "Car does not exist");



    private final String message;
    private String code;

    private ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}