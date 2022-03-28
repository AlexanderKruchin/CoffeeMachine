package com.machine.coffeemachine.Controller;

import com.machine.coffeemachine.model.Message;
import com.machine.coffeemachine.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RestController

public class MachineController {

    @Autowired
    private MessageRepo messageRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/createCoffee")
    public @ResponseBody
    String addNewCoffee(@RequestParam String name) {

        Message n = new Message();
        n.setCoffeeName(name);
        messageRepo.save(n);
        return "Saved";
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
