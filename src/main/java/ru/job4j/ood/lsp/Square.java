package ru.job4j.ood.lsp;

/**
 * Используя Rectangle r = new Square();,
 * ожидаем что значение r.getArea() будет равно s.getArea() (Rectangle s = new Rectangle();)
 * но это не так, потому что в Square сетеры переопределены
 */

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
