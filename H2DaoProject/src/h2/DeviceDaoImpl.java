package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DeviceDaoImpl implements DeviceDao {
    private static final String DEVICE_SELECT = "SELECT * FROM H2BASE.MYBASE WHERE id = ?";
    private static final String DEVICE_SHOW = "SELECT * FROM H2BASE.MYBASE";
    private static final String DEVICE_INSERT = "INSERT INTO H2BASE.MYBASE (DEVICE, DISPLEY, CAMERA, WIFI, DATE) VALUES (?, ?, ?, ?, ?)";
    private static final String DEVICE_UPDATE = "UPDATE H2BASE.MYBASE SET DEVICE=?, DISPLEY=?, CAMERA=?, WIFI=?, DATE=? WHERE ID=?";
    private static final String DEVICE_DELETE = "DELETE FROM H2BASE.MYBASE WHERE ID=?";
    private static final String DEVICE_COUNT = "SELECT COUNT(*) FROM H2BASE.MYBASE";
    private ConnectionSource connectionSource;
    private List<DeviceModel> list = new LinkedList<DeviceModel>();

    public DeviceDaoImpl(ConnectionSource connection) {
        connectionSource = connection;
    }

    public int count() throws SQLException {
        Connection connection = connectionSource.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(DEVICE_COUNT);
            rs = stm.executeQuery();
            rs.next();
            return rs.getInt(1);

        } finally {
            if (rs != null)
                rs.close();

            if (stm != null)
                stm.close();
            connection.close();

        }
    }

    @Override
    /**
     * создание объекта Device
     */
    public void create(DeviceModel deviceModel) throws SQLException {
        Connection connection = connectionSource.getConnection();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(DEVICE_INSERT);
            stm.setString(1, deviceModel.getDevice());
            stm.setInt(2, deviceModel.getDispley());
            stm.setInt(3, deviceModel.getCamera());
            stm.setBoolean(4, deviceModel.hasWifi());
            stm.setDate(5, new java.sql.Date(deviceModel.getDate().getTime()));
            if (stm.executeUpdate() == 0 ) System.out.println("Result is empty");
        } finally {
            if (stm != null)
                stm.close();
            connection.close();
        }

    }

    @Override
    /**
     * 
     */
    public DeviceModel read(int key) throws SQLException {
        Connection connection = connectionSource.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(DEVICE_SELECT);
            stm.setInt(1, key);
            rs = stm.executeQuery();
            rs.next();
            DeviceModel deviceModel = new DeviceModel();
            deviceModel.setId(rs.getInt("ID"));
            deviceModel.setDevice(rs.getString("device"));
            deviceModel.setDispley(rs.getInt("displey"));
            deviceModel.setCamera(rs.getInt("camera"));
            deviceModel.setWifi("YES".equalsIgnoreCase(rs.getString("wifi")));
            deviceModel.setDate(rs.getDate("date"));
            return deviceModel;
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            connection.close();
        }

    }

    @Override
    /**
     * Обновление записи по ключу
     */
    public void update(int key, DeviceModel deviceModel) throws SQLException {
        Connection connection = connectionSource.getConnection();
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(DEVICE_UPDATE);
            stm.setString(1, deviceModel.getDevice());
            stm.setInt(2, deviceModel.getDispley());
            stm.setInt(3, deviceModel.getCamera());
            stm.setBoolean(4, deviceModel.hasWifi());
            stm.setDate(5, new java.sql.Date(deviceModel.getDate().getTime()));
            stm.setInt(6, key);
            if (stm.executeUpdate() == 0 ) System.out.println("Result is empty");
        } finally {
            if (stm != null)
                stm.close();
            connection.close();
        }

    }

    @Override
    /**
     * Удаление данных по ID
     */
    public void delete(int key) throws SQLException {
        Connection connection = connectionSource.getConnection();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(DEVICE_DELETE);
            stm.setInt(1, key);
            if (stm.executeUpdate() == 0 ) System.out.println("Result is empty");
        } finally {
            if (stm != null)
                stm.close();
            connection.close();
        }

    }

    @Override
    /**
     * Вывод всех элементов бд
     */
    public List<DeviceModel> getAll() throws SQLException {
        Connection connection = connectionSource.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        list.clear();
        try {
            stm = connection.prepareStatement(DEVICE_SHOW);
            rs = stm.executeQuery();
            while (rs.next()) {
                DeviceModel deviceModel = new DeviceModel();
                deviceModel.setId(rs.getInt("ID"));
                deviceModel.setDevice(rs.getString("device"));
                deviceModel.setDispley(rs.getInt("displey"));
                deviceModel.setCamera(rs.getInt("camera"));
                deviceModel.setWifi("YES".equalsIgnoreCase(rs.getString("wifi")));
                deviceModel.setDate(rs.getDate("date"));
                list.add(deviceModel);
            }
        } finally {
            if (rs != null)
                rs.close();
            if (stm != null)
                stm.close();
            connection.close();
        }
        return list;
    }

    @Override
    /**
     * create table
     */
    public void createTable() throws SQLException {
        Connection connection = connectionSource.getConnection();
        Statement stm = null;
        try{
        stm = connection.createStatement();
        stm.execute("CREATE TABLE MYBASE (ID INT AUTO_INCREMENT, Device VARCHAR(30), Displey INT(2), Camera INT(2), WIFI VARCHAR(5), Date DATE);");
        stm.execute("CREATE TABLE H2BASE.MYBASE (ID INT AUTO_INCREMENT, Device VARCHAR(30), Displey INT(2), Camera INT(2), WIFI VARCHAR(5), Date DATE);");
        } finally {
            if (stm != null)
                stm.close();
            connection.close();
        }
    }
}
