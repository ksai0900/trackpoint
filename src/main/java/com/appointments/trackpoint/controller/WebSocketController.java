package com.appointments.trackpoint.controller;

import com.appointments.trackpoint.service.AppointmentsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;


@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate template;

    public void notifyNewAppointment() {
        System.out.println("Sending notification...");
        try {
            this.template.convertAndSend("/newAppointments", new Message("New appointments available!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    class Message {
        private String content;

        public Message(String content) {
            this.content = content;
        }
    }
}