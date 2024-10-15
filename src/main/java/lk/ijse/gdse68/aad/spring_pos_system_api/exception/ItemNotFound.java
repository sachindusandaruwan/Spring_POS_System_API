package lk.ijse.gdse68.aad.spring_pos_system_api.exception;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound() {}
    public ItemNotFound(String message) {
        super(message);
    }
    public ItemNotFound(String message, Throwable cause) {}
}
