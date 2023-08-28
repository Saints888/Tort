package com.example.web.service;

import java.util.Objects;

public class TortInfo {
    // Класс описывает информацию о торте
// Включает в себя поля: id, name, description, price, weight
    private String id, name, description;    //идентификатор, название, описание
    private int price;                       //цена
    private int weight;                      //вес

    public TortInfo() { // Объявление пустого конструктора TortInfo
    }

    public String getId() {
        return id;
    }
    // Метод getId()
    // Возвращает значение поля id


    public void setId(String id) {
        this.id = id;
    }
    // Метод setId()
    // Устанавливает значение поля id

    public String getName() {
        return name;
    }
    // Метод getName()
    // Возвращает значение поля name

    public void setName(String name) {
        this.name = name;
    }
    // Метод setName()
    // Устанавливает значение поля name

    public String getDescription() {
        return description;
    }
    // Метод getDescription()
    // Возвращает значение поля description

    public void setDescription(String description) {
        this.description = description;
    }
    // Метод setDescription()
    // Устанавливает значение поля description
    public int getPrice() {
        return price;
    }
    // Метод getPrice()
    // Возвращает значение поля price


    public void setPrice(int price) {
        this.price = price;
    }
    // Метод setPrice()
    // Устанавливает значение поля price


    public int getWeight() {
        return weight;
    }
    // Метод getWeight()
// Возвращает значение поля weight

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Метод setWeight()
// Устанавливает значение поля weight

    /**
     * Создание id города на основе названия и хэшкода торта
     */
    public void createId() {
    this.id = "" + hashCode();
    }
    // Комментарий к методу createId()
// Создание идентификатора города на основе названия и хеш-кода торта

    @Override
    public boolean equals(Object ob) {
        if (this == ob) return true;
        if (ob == null) return false;
        if (this.getClass() != ob.getClass())   return false;
        TortInfo info =(TortInfo) ob;
        return id.equals(info.id) && name.equalsIgnoreCase(info.name);
    }

    // Переопределение метода equals()
// Проверка равенства объектов по их id и name
// Если объекты равны по этим полям, то возвращается true, иначе false

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
    // Переопределение метода hashCode()
// Вычисление хеш-кода объекта на основе name, приведенного к нижнему регистру

    @Override
    public String toString () {
        return "TortInfo{" +
                "id='" + id + '\'' +
                ", name'" + name +'\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", weight" + weight + '}';

    }
}
// Переопределение метода toString()
// Формирование строки, содержащей значения всех полей класса


