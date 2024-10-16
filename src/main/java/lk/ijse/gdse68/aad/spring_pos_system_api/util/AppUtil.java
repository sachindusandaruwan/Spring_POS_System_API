package lk.ijse.gdse68.aad.spring_pos_system_api.util;

import java.time.LocalDateTime;
import java.util.Random;

public class AppUtil {
    public static String createOrderId(){
        return "OR"+new Random().nextInt(10000);
    }

    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now();
    }

    public static String createOrderDetailsId(){
        return "OD"+new Random().nextInt(10000);
    }

}
