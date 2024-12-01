import static java.lang.System.out;
import java.util.Scanner;
public class Main {
    public static <T> void printBoxContents(Box<T> box) {
        try {
            out.println("Значение в коробке: " + box.get());
        }
        catch (RuntimeException e) {
            out.println("Ошибка: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main m = new Main();
        out.println("1.1");
        try{
        Box<Integer> intBox = new Box<>();//создание коробки типа int
        intBox.put(3);
        printBoxContents(intBox); // Передача коробки в метод
        }
        catch (RuntimeException e) {
            out.println("Ошибка: " + e.getMessage());
        }
        out.println("1.3");
    }
}
