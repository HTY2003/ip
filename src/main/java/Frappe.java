public class Frappe {

    public static void printUnderscoreLine() {
        System.out.println(new String(new char[50]).replace("\0", "_")
        );
    }
    public static void main(String[] args) {
        printUnderscoreLine();
        System.out.println("Hello! I'm Frappe.");
        System.out.println("What can I do for you?");
        printUnderscoreLine();
        System.out.println("Bye. Hope to see you again soon!");
        printUnderscoreLine();
    }
}