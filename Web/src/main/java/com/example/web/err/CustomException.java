package com.example.web.err;

public class CustomException  extends RuntimeException{
    private final String code;    // Приватное поле для хранения кода исключения


    public CustomException(String code, String message) {
        super(message);
        this.code = code;
        // Конструктор класса CustomException, принимает код и сообщение и вызывает
        // конструктор родительского класса RuntimeException с передачей в него сообщения.
        // Затем сохраняет значение кода в переменную code.

        // @param code код исключения
        // @param message сообщение исключения
    }

    public CustomException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code =code;
        //Конструктор класса CustomException, принимает сообщение, причину и код и вызывает
        //      конструктор родительского класса RuntimeException с передачей в него сообщения и причины.
        //      Затем сохраняет значение кода в переменную code.
        //
        //      @param message сообщение исключения
        //      @param cause причина исключения
        //      @param code код исключения
    }

    public String getCode() {
        return code;
        //Метод getCode() возвращает значение кода исключения.
        //
        //      @return код исключения
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "message=" + getMessage() +
                " code=" + code + '}';
        //Переопределенный метод toString(), который возвращает строковое представление
        //      объекта CustomException.
        //      Метод возвращает строку, в которой выводятся значение сообщения и значение кода.
        //
        //      @return строковое представление объекта CustomException
    }
}
