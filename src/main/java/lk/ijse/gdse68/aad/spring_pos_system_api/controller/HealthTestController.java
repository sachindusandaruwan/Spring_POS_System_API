package lk.ijse.gdse68.aad.spring_pos_system_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthtest")
public class HealthTestController {
    @GetMapping
    public String healthTest(){
        return "Server Controller run Successfully!!";
    }
}
