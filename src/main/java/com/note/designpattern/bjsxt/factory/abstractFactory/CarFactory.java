package com.note.designpattern.bjsxt.factory.abstractFactory;

public interface CarFactory {
    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}