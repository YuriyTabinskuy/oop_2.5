package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BookDAO dao = new BookDAO();
            dao.createTable();

            dao.insert(new Book("Clean Code", "Robert Martin", 29.99, 10));
            dao.insert(new Book("Effective Java", "Joshua Bloch", 39.99, 5));

            List<Book> books = dao.getAll();
            books.forEach(System.out::println);

            dao.updateStock(1, 15);
            System.out.println("After stock update:");
            dao.getAll().forEach(System.out::println);

            dao.delete(2);
            System.out.println("After delete:");
            dao.getAll().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
