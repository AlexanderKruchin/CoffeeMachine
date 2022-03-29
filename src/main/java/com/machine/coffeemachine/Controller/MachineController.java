package com.machine.coffeemachine.Controller;

import com.machine.coffeemachine.model.CoffeeCount;
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

    CoffeeCount coffeeCount = new CoffeeCount();


    @Autowired
    private MessageRepo messageRepo;

    @RequestMapping(method = RequestMethod.POST, path = "/createCoffee")
    public @ResponseBody
    String addNewCoffee(@RequestParam String coffeeName) {

        Message message = new Message();


        switch (coffeeName) {
            case "Americano":
                if (coffeeCount.getCoffee() < 20) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончился кофе, пополните лоток!");
                } else if (coffeeCount.getWater() < 250) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончилась вода, пополните лоток!");
                }
                message.setCoffeeName(coffeeName);
                messageRepo.save(message);
                coffeeCount.setCoffee(coffeeCount.getCoffee() - 20);
                coffeeCount.setWater(coffeeCount.getWater() - 250);
                return "Вы выбрали Американо";

            case "Latte":
                if (coffeeCount.getCoffee() < 15) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончился кофе, пополните лоток!");
                } else if (coffeeCount.getWater() < 250) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончилась вода, пополните лоток!");
                } else if (coffeeCount.getMilk() < 50) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончилось молоко, пополните лоток!");
                }
                message.setCoffeeName(coffeeName);
                messageRepo.save(message);
                coffeeCount.setCoffee(coffeeCount.getCoffee() - 15);
                coffeeCount.setWater(coffeeCount.getWater() - 250);
                coffeeCount.setMilk(coffeeCount.getMilk() - 50);
                return "Вы выбрали Латте";

            case "Espresso":
                if (coffeeCount.getCoffee() < 15) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончился кофе, пополните лоток!");
                } else if (coffeeCount.getWater() < 70) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Закончилась вода, пополните лоток!");

                }
                message.setCoffeeName(coffeeName);
                messageRepo.save(message);
                coffeeCount.setCoffee(coffeeCount.getCoffee() - 15);
                coffeeCount.setWater(coffeeCount.getWater() - 70);
                return "Вы выбрали Эспрессо.";
            default:
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Такой кофе отсутствует. Повторите ввод.");
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "/statusCheck")
    public @ResponseBody
    String statusCheck(){
        return " Воды в кофемашине: " + coffeeCount.getWater()
                + "\n Кофе в кофемашине: " + coffeeCount.getCoffee()
                + "\n Молока в кофемашине: " + coffeeCount.getMilk();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/drinks")
    public @ResponseBody
    String drinks(){
        return "Вы можете выбрать следующие напитки:" +
                "\nAmericano - Американо (20гр. кофе + 250мл. воды) " +
                "\nLatte - Латте (15гр. кофе + 250мл. воды + 50мл. молока) " +
                "\nEspresso - Эспрессо (15гр. кофе + 70мл. воды)";
    }

    @GetMapping(path = "/addFullMilk")
    public void addMilk(){
        coffeeCount.setMilk(1000);
    }

    @GetMapping(path = "/addFullWater")
    public void addWater(){
        coffeeCount.setWater(3000);
    }

    @GetMapping(path = "/addFullCoffee")
    public void addCoffee(){
        coffeeCount.setCoffee(900);
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
