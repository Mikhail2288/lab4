public class Box<T>{
    private T item;
    
    public Box() {//конструктор, в начале коробка пустая
        this.item = null;
    }
    
    public void put(T item) {//метод для того, чтобы положить предмет в коробку
        if (this.item != null) throw new RuntimeException("Коробка не пуста!");
        this.item = item;
    }

    public T get() {//метод для того, чтобы достать предмет из коробки
        if (this.item == null) throw new RuntimeException("Коробка пустая!");
        T temp = this.item;
        this.item = null;
        return temp;
    }
    
    public boolean isEmpty() {//метод проверки на заполненность
        return this.item == null;
    }
}
