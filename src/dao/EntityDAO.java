package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class EntityDAO<E, K> {

    private Connection connection;
    private DAO dao;

    public EntityDAO() {
        dao.connection.init();
        connection = dao.connection.getConnect();
    }

    public abstract List<E> getAll();

    public abstract boolean update(E entity);

    public abstract E getEntityById(K id);

    public abstract boolean delete(K id);

    public abstract boolean create(E entity);

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
