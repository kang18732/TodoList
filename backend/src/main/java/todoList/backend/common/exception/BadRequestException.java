package todoList.backend.common.exception;

public class BadRequestException extends RuntimeException {


    private static final long serialVersionUID = -3807394796255346596L;

    public BadRequestException() {
        super("잘못된 요청입니다.");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
