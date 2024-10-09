package lk.ijse.gdse68.aad.spring_pos_system_api.exception;

public class DataPersistFailException extends RuntimeException {
    public DataPersistFailException() {super();}

    public DataPersistFailException(String message) {
        super(message);
    }
    public DataPersistFailException(String message, Throwable cause) {}
}
