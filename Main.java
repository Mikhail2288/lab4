import static java.lang.System.out;
import java.util.Scanner;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
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
        
        out.println("2.2");
        Box<Integer> intBox = new Box<>();
        intBox.put(3);

        Box<Double> doubleBox = new Box<>();
        doubleBox.put(3.14);

        Box<Float> floatBox = new Box<>();
        floatBox.put(2.7f);
        Box<String> strBox = new Box<>();
        strBox.put("gs");
    

        BoxOperations boxOps = new BoxOperations(List.of(intBox, doubleBox, floatBox));
        out.println("Maximum value: " + boxOps.findMax());
    }
}
