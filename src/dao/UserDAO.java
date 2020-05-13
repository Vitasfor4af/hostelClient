package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends EntityDAO<User, Integer> {

    private static final String SQL_UPDATE_USER_BY_ID =
            "UPDATE users SET roleID = ?, login = ?, password = ?, name = ? WHERE id = ?";

    private static final String SQL_GET_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";

    private static final String SQL_CREATE_USER =
            "INSERT INTO users (roleID, login, password, name) VALUES (?,?,?,?)";

    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id = ?";

    private static final String SQL_GET_ALL =
            "SELECT users.id, users.name, users.login, users.password, role.name FROM users JOIN role ON users.roleID = role.id;";

    @Override
    public List<User> getAll() {
        List<User> list = new LinkedList<>();
        PreparedStatement ps = getPrepareStatement(SQL_GET_ALL);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setRole(rs.getString("role.name"));

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return list;
    }

    @Override
    public User getEntityById(Integer id) {
        User user = new User();

        PreparedStatement ps = getPrepareStatement(SQL_GET_USER_BY_ID);
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setRole(rs.getString("role.name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
        }

        return user;
    }

    @Override
    public boolean delete(Integer id) {
        PreparedStatement st = getPrepareStatement(SQL_DELETE_USER_BY_ID);

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
    public boolean update(User user) {
        PreparedStatement ps = getPrepareStatement(SQL_UPDATE_USER_BY_ID);
        try {
            choseRole(user, ps);
            ps.setInt(5, user.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void choseRole(User user, PreparedStatement ps) throws SQLException {
        int role = 0;
        switch (user.getRole()){
            case "user":
                role = 1;
                break;
            case "admin":
                role = 2;
                break;
            case "worker":
                role = 3;
                break;
        }
        ps.setInt(1, role);
        ps.setString(2, user.getLogin());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getName());
    }

    @Override
    public boolean create(User user) {
        PreparedStatement ps = getPrepareStatement(SQL_CREATE_USER);
        try {
            choseRole(user, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
