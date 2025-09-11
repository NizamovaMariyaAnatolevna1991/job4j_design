package ru.job4j.ood.isp;

/**
 * Когда стали реализовывать поняли, что большая часть методов для TV
 * не нужна
 * мы вынуждены их игнорировать, либо выбрасывать исключения
 */

public class TV implements UniversalRemote {
    @Override
    public void turnOnTV() {
        System.out.println("TV включён");
    }

    @Override
    public void changeChannel() {
        System.out.println("Канал изменён");
    }

    @Override
    public void adjustVolume() {
        System.out.println("Громкость изменена");
    }

    @Override
    public void startWashing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWashMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void heatOven() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTimer() {
        throw new UnsupportedOperationException();
    }
}
