package lk.ijse.gdse68.aad.spring_pos_system_api.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {super();}
    public CustomerNotFoundException(String message) {
        super(message);
    }
    public CustomerNotFoundException(String message, Throwable cause) {}
}
