package com.machine.coffeemachine.Controller;

import com.machine.coffeemachine.model.Message;
import com.machine.coffeemachine.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RestController

public class MachineController {

    @Autowired
    private MessageRepo messageRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/createCoffee")
    public @ResponseBody
    String addNewCoffee(@RequestParam String coffeeName) {

        Message message = new Message();



        if (coffeeName.equals("Americano")){
            message.setCoffeeName(coffeeName);
            messageRepo.save(message);
            return "Вы выбрали Американо";
        } else if(coffeeName.equals("Latte")){
            message.setCoffeeName(coffeeName);
            messageRepo.save(message);
            return "Вы выбрали Латте";
        }
        else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Такой кофе отсутствует. Повторите ввод.");
        }

    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Message> getAllCoffee() {

        return messageRepo.findAll();
    }

    @DeleteMapping(path = "/delete")
    public void deleteCoffee(@RequestBody Message message) {

        messageRepo.delete(message);
    }
}
