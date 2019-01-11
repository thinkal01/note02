package com.note.designpattern.factory.abstractfactory;

public class DefaultFactory extends AbstractFactory {

    @Override
    public Food createFood() {
        return new Apple();
    }

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }

    @Override
    public Weapon createWeapon() {
        return new AK47();
    }

}
