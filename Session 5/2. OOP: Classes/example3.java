public class example3 {
    public static void main(String[] args) {
        MyString myString = new MyString();
        char[] charArray = {'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd'};
        String substring = myString.substringById(3, charArray);
        System.out.println(substring); // Output: "loWorld"
    }
}

class MyString {
    public String substringById(int id, char[] charArray) {
        if (id >= 0 && id < charArray.length) {
            return new String(charArray, id, charArray.length - id);
        } else {
            return "";
        }
    }
}