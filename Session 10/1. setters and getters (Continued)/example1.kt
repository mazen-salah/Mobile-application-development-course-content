class Employee() {
    var age: Int = 0
        get() = field*2
        set(value) {
            field = value
        }
}

fun main() {
    val employee = Employee()
  	employee.age = 30 ;
    println(employee.age);
}