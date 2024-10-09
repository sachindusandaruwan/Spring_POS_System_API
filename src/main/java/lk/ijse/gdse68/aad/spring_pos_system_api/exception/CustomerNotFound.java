package lk.ijse.gdse68.aad.spring_pos_system_api.exception;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound() {}
    public CustomerNotFound(String message) {
        super(message);
    }
    public CustomerNotFound(String message, Throwable cause) {}
}
