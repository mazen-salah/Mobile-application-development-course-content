interface Shape {
    var radius:Double;
    fun area();
}

class Circle : Shape {
    override 
    var radius:Double = 0.0;
    
    override 
    fun area(){
        println( 3.14 * radius *radius)
    }
}

fun main() {
    var circle = Circle();
    circle.radius = 2.0
    circle.area();
}