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
        } catch (RuntimeException e) {
            out.println("Ошибка: " + e.getMessage());
        }
    }
    //статический метод, который принимает Список элементов типа Т и определяет функцию преобразования каждого элемента входного списка
    public static <T, R> List<R> transformList(List<T> inputList, Function<T, R> function) {
        List<R> result = new ArrayList<>();//пустой список ArrayList типа R, в которой будут храниться результаты преобразования.
        for (T item : inputList) {
            try {
                result.add(function.apply(item));//принимает один аргумент типа T и возвращает результат типа R, то есть применение функции к аргументу.
            } catch (RuntimeException e) {
                System.err.println("Ошибка при применении функции к элементу: " + item + ", причина: " + e.getMessage());
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main m = new Main();

        out.println("1.1");
        try {
            Box<Integer> intBox = new Box<>(); // создание коробки типа int
            intBox.put(3);
            printBoxContents(intBox); // Передача коробки в метод
        } catch (RuntimeException e) {
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

        out.println("3.1");
        
        //Список строк -> Список длин строк
        List<String> strings = List.of("qwerty", "asdfg", "zx");
        List<Integer> stringLengths = transformList(strings, String::length);//ссылка на метод
        out.println("Длины строк: " + stringLengths);
        
        //Список чисел -> Список с положительными числами
        List<Integer> numbers = List.of(1, -3, 7);
        List<Integer> positiveNumbers = transformList(numbers, n -> n < 0 ? -n : n);// лямбда-выражение
        out.println("Положительные числа: " + positiveNumbers);


        //Список массивов -> Список максимальных значений 
        List<int[]> intArrays = List.of(
                new int[]{1, 5, 2, 8},
                new int[]{3, -1, 7, 4},
                new int[]{9, 2, 6, 1}
        );
        List<Integer> maxValues = transformList(intArrays, arr -> Arrays.stream(arr).max().orElse(0));//лямбда-выражение
        out.println("Максимальные значения: " + maxValues);
    }
}
