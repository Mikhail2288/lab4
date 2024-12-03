import java.util.Collection;
import java.util.List;

public class BoxOperations<T extends Number> {

    private Collection<Box<? extends Number>> boxes;//маска типов Number

    public BoxOperations(Collection<Box<? extends Number>> boxes) {
        if (boxes.isEmpty()) throw new RuntimeException("Коллекция пустая");// Проверка, что коллекция не пуста
        this.boxes = boxes;
    }

    public double findMax() {
        double max = 0;
        boolean first = true;

        for (Box<? extends Number> box : boxes) {
                double value = box.get().doubleValue();
                if (first) {
                    max = value;
                    first = false;
                } else max = Math.max(max, value);
        }
        return max;
    }
}
