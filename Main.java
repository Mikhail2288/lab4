import static java.lang.System.out;
import java.util.Scanner;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BiFunction;

public class Main {
    public static <T> void printBoxContents(Box<T> box) {
        try {
            out.println("Значение в коробке: " + box.get());
        } catch (RuntimeException e) {
            out.println("Ошибка: " + e.getMessage());
        }
    }
    //статический метод, который принимает Список элементов типа Т и функцию преобразования каждого элемента входного списка
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
    //статический метод, который принимает Список элементов типа Т и предикат
    public static <T> List<T> filterList(List<T> inputList, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : inputList) {
            if (predicate.test(item)) {//для каждого элемента вызывается метод test() предиката.
                result.add(item);//Если test() возвращает true  то элемент добавляется в результирующий список.
            }
        }
        return result;
    }
    //статический метод, который принимает Список элементов типа Т, Функциональный интерфейс BinaryOperator<T> и начальное значение результирующей переменной
    public static <T, R> R reduceList(List<T> inputList, BiFunction<R, T, R> accumulator, R initialValue) {
        if (inputList == null || inputList.isEmpty()) {//Обработка пустого списка
            return initialValue; // Возвращаем начальное значение при пустом списке
        }

        R result = initialValue; // Начальное значение
        for (T item : inputList) {
            result = accumulator.apply(result, item); //применяет заданную операцию accumulator к текущему значению result и элементу item
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
        intBox.put(9);

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
        
        out.println("3.2");
        
        //Фильтрация строк по длине
        List<String> strings2 = List.of("qwerty", "asdfg", "zx", "abc", "a");
        List<String> filteredStrings2 = filterList(strings2, str -> str.length() >= 3);//лямбда-выражение, которое определяет предикат
        out.println("Отфильтрованные строки: " + filteredStrings2);

        //Фильтрация чисел по знаку
        List<Integer> numbers2 = List.of(1, -3, 7, -5, 0, 2);
        List<Integer> filteredNumbers2 = filterList(numbers2, n -> n <= 0);//лямбда-выражение, определяющее предикат
        out.println("Отфильтрованные числа: " + filteredNumbers2);

        //Фильтрация массивов по отрицательным числам
        List<int[]> intArrays2 = List.of(
                new int[]{-1, -2, -3},
                new int[]{3, -1, 7, 4},
                new int[]{-9, -2, -6, -1},
				new int[]{1, 2, 3}  // добавлен массив с положительными числами
        );
        List<int[]> filteredArrays2 = filterList(intArrays2, arr -> Arrays.stream(arr).allMatch(n -> n < 0));//Предикат для фильтрации. Он принимает массив целых чисел (arr) и проверяет, являются ли все элементы массива отрицательными.
        out.print("Отфильтрованные массивы: ");
        for (int i = 0; i < filteredArrays2.size(); i++) {
            out.print(Arrays.toString(filteredArrays2.get(i)));
            if (i < filteredArrays2.size() - 1) {
                out.print(", ");
            }
        }
        
        out.println("3.3");
        //Объединение строк
        List<String> strings3 = List.of("qwerty", "asdfg", "zx");
        String concatenatedString3 = reduceList(strings3, (a, b) -> a + b, ""); //лямбда-выражение, которое определяет операцию редукции
        out.println("Объединенная строка: " + concatenatedString3);

        //Сумма чисел
        List<Integer> numbers3 = List.of(1, -3, 7);
        Integer sum3 = reduceList(numbers3, Integer::sum, 0);//ссылка на метод sum класса Integer
        out.println("Сумма чисел: " + sum3);

        //Общее количество элементов в списках
        List<List<Integer>> listOfLists3 = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8,9)
        );
		
        Integer totalElements3 = reduceList(listOfLists3, (a, b) -> a + b.size(), 0);//Лямбда-выражение. Оно принимает целое число a (аккумулированная сумма) и список целых чисел b. Вычисляет размер списка b с помощью b.size() и добавляет его к a.
        System.out.println("Общее количество элементов: " + totalElements3);
    }
}
