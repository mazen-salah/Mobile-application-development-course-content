abstract class Employee(val name:String){
    fun sayMyName(){
        println(name);
    }
}

class Manager(name: String) : Employee(name) {
   
}

fun main() {
    val manager = Manager("Mazen");
    manager.sayMyName();
}