package com.deciploy.backend.modules.socket.command;

import com.deciploy.backend.modules.socket.command.dto.Command;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CommandController {

    @MessageMapping("/command")
    @SendTo("/topic/command")
    public Command command(Command command) {
        return command;
    }
}
