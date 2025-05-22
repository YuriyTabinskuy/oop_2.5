package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void createTable() throws Exception {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                id INT AUTO_INCREMENT PRIMARY KEY,
                title VARCHAR(255),
                author VARCHAR(255),
                price DOUBLE,
                stock INT
            )
        """;

        try (Statement stmt = DBUtil.getConnection().createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insert(Book book) throws Exception {
        String sql = "INSERT INTO books (title, author, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getStock());
            ps.executeUpdate();
        }
    }

    public List<Book> getAll() throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement stmt = DBUtil.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    public void updateStock(int id, int newStock) throws Exception {
        String sql = "UPDATE books SET stock = ? WHERE id = ?";
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, newStock);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
