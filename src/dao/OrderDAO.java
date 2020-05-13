package dao;

import model.Order;
import model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO extends EntityDAO<Order, Integer> {


    private static final String SQL_UPDATE_ORDER_BY_ID =
            "UPDATE users SET roleID = ?, login = ?, password = ?, name = ? WHERE id = ?";

    private static final String SQL_GET_ORDER_BY_ID =
            "SELECT * FROM users WHERE id = ?";

    private static final String SQL_CREATE_ORDER =
            "INSERT INTO orders (number, date_start, date_end, user) VALUES (?,?,?,?)";

    private static final String SQL_DELETE_ORDER_BY_ID =
            "DELETE FROM orders WHERE number = ?";

    private static final String SQL_GET_ALL =
            "SELECT users.id, users.name, users.login, users.password, role.name FROM users JOIN role ON users.roleID = role.id;";

    private static final String SQL_GET_ALL_BY_ID =
            "SELECT * FROM orders WHERE user = ?;";

    @Override
    public List<Order> getAll() {
        List<Order> list = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SQL_GET_ALL);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setNumber(rs.getInt(1));
                order.setDateStart(rs.getDate(2));
                order.setDateEnd(rs.getDate(3));
                order.setUser(rs.getString(4));


                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return list;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public Order getEntityById(Integer id) {
        Order order = new Order();


        PreparedStatement ps = getPrepareStatement(SQL_GET_ORDER_BY_ID);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setNumber(rs.getInt(1));
                order.setDateStart(rs.getDate(2));
                order.setDateEnd(rs.getDate(3));
                order.setUser(rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return order;
    }

    @Override
    public boolean delete(Integer id) {
        PreparedStatement st = getPrepareStatement(SQL_DELETE_ORDER_BY_ID);

        boolean isRemoved = false;

        try {
            st.setInt(1, id);
            int i = st.executeUpdate();
            isRemoved = i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isRemoved;
    }

    @Override
    public boolean create(Order entity) {
        PreparedStatement ps = getPrepareStatement(SQL_CREATE_ORDER);
        try {
            ps.setInt(1, entity.getNumber());
            ps.setDate(2, (Date) entity.getDateStart());
            ps.setDate(3, (Date) entity.getDateEnd());
            ps.setString(4, entity.getUser());

            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Order> getAllByUser(String command) {

        List<Order> listOrder = new ArrayList<>();
        PreparedStatement ps = getPrepareStatement(SQL_GET_ALL_BY_ID);

        try {
            ps.setInt(1, Integer.parseInt(command));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setNumber(rs.getInt(1));
                order.setDateStart(rs.getDate(2));
                order.setDateEnd(rs.getDate(3));
                order.setUser(rs.getString(4));


                listOrder.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return listOrder;
    }
}
