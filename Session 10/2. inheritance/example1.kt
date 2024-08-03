open class Animal() {
    var name = "";
    var color = "";
    
    open fun makeSound(){
            println("[Sound]");
        }
}

class Cat : Animal(){
    override
    fun makeSound(){
        println("[Meow]");
    }
}

class Dog : Animal(){
    override
    fun makeSound(){
        println("[Woof]");
    }
}

fun main() {
   	var animal = Animal();
   	var dog = Dog();
   	var cat = Cat();
   
   	animal.makeSound();
    dog.makeSound();
    cat.makeSound();
}