package escenario1;

public class Variables {
    public static void main(String[] args) {
        int x = 6;
        int y = 5;
        boolean a = true;
        boolean b = false;
        System.out.println("Addition: "+(x+y));
        System.out.println("Subtraction: "+(x-y));
        System.out.println("Multiplication: "+(x*y));
        System.out.println("Division: "+(x/y));
        System.out.println("MModulus: "+(x%y));
        System.out.println("Less than: "+(x<y));
        System.out.println("Greater than: "+(x>y));
        System.out.println("Equal to: "+(x==y));
        System.out.println("Not equal to: "+(x!=y));
        System.out.println("Logical AND: "+(a && b));
        System.out.println("Logical OR: "+(a || b));
        System.out.println("Logical NOT: "+(!a));
    }
}
