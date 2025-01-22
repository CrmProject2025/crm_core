// package com.crm.tehnomer.entities;


// @Entity
// @Table(name = "student")
// public class Client {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;

//     @Column(length = 255, nullable = false)
//     private String name;

//     @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
//     // @BatchSize(size = 2)
//     private List<Achievement> achievements = new ArrayList();

//     public List<Achievement> getAchievements() {
//         return achievements;
//     }

//     public void setAchievements(List<Achievement> achievements) {
//         this.achievements = achievements;
//     }

//     public long getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     @Override
//     public int hashCode() {
//         final int prime = 31;
//         int result = 1;
//         result = prime * result + (int) (id ^ (id >>> 32));
//         return result;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj)
//             return true;
//         if (obj == null)
//             return false;
//         if (getClass() != obj.getClass())
//             return false;
//         Student other = (Student) obj;
//         if (id != other.id)
//             return false;
//         return true;
//     }

//     @Override
//     public String toString() {
//         return "Student [id=" + id + ", name=" + name + "]";
//     }

// }
