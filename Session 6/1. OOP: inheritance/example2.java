public class example2 {
 public static void main(){
   
 }
}

enum AnimalType {
    CAT, DOG
}

class Animal {
    String name;
    AnimalType type;

    void eat() {
        System.out.println("Eating...");
    }
}

class Cat extends Animal {
    this.type = AnimalType.CAT;
    int age;
    String color;
}

class Dog extends Animal {
    this.type = AnimalType.DOG;
    int age;
    String color;
}