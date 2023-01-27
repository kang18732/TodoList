package todoList.backend.common.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6338022025760286415L;

    public NotFoundException() {
        super("존재하지 않는 페이지입니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
