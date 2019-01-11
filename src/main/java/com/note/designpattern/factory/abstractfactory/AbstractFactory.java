package com.note.designpattern.factory.abstractfactory;

public abstract class AbstractFactory {
    public abstract Vehicle createVehicle();

    public abstract Weapon createWeapon();

    public abstract Food createFood();
}
