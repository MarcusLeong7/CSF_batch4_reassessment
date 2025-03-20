package vttp2023.assessment.csf.orderbackend.repositories;

public class Queries {

    public static String SQL_INSERT_ORDER = "INSERT INTO orders VALUES (?,?,?,?)";
    public static String SQL_GET_ORDERS = "SELECT * FROM orders WHERE email = ?";
}
