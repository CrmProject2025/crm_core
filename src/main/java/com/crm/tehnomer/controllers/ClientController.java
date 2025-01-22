package com.crm.tehnomer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.tehnomer.services.EmailService;

@RestController
public class ClientController {
    @Autowired
    private EmailService emailService;

    // private StudentRepository studentRepository;

    // private AchievementRepository achievementRepository;

    public ClientController() {

    }

    @GetMapping("/student")
    public String getMethodName() {
        System.out.println("###############");

        emailService.sendSimpleEmail("potsanovik@mail.ru", "Тема письма", "Текст письма");

        System.out.println("###############");
        return "asd111";

    }
    // // studentRepository.deleteById(list.getFirst().getId());

    // return "123";
    // }

    // @GetMapping("/achievements")
    // public String getMethodName2() {
    // List<Achievement> list = achievementRepository.findAll();
    // for (Achievement achievement : list) {
    // System.out.println(achievement.getId());
    // System.out.println(achievement.getStudents());
    // System.out.println(achievement);
    // System.out.println("###############");

    // }
    // return "123";
    // }

}
