// package com.crm.tehnomer.controllers;

// @RestController
// public class ClientController {

//     private StudentRepository studentRepository;

//     private AchievementRepository achievementRepository;

//     public StudentController(StudentRepository studentRepository,
//             AchievementRepository achievementRepository) {
//         this.studentRepository = studentRepository;
//         this.achievementRepository = achievementRepository;
//     }

//     @GetMapping("/student")
//     public String getMethodName() {
//         List<Student> list = studentRepository.findAll();
//         for (Student student : list) {
//             System.out.println(student);
//             System.out.println(student.getId());
//             System.out.println(student.getName());
//             System.out.println(student.getAchievements());
//             System.out.println("###############");

//         }
//         // studentRepository.deleteById(list.getFirst().getId());

//         return "123";
//     }

//     @GetMapping("/achievements")
//     public String getMethodName2() {
//         List<Achievement> list = achievementRepository.findAll();
//         for (Achievement achievement : list) {
//             System.out.println(achievement.getId());
//             System.out.println(achievement.getStudents());
//             System.out.println(achievement);
//             System.out.println("###############");

//         }
//         return "123";
//     }



// }

