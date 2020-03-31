package dao;

import data.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private static ClientDao clientDao = null;
    private Connection conn;

    private ClientDao(Connection conn) {
        this.conn = conn;
    }

    public static ClientDao getInstance(Connection conn) {
        if (clientDao == null) {
            clientDao = new ClientDao(conn);
        }

        return clientDao;
    }

    public List<Client> getClientByVolNum(int volNum) throws SQLException {
        List<Client> clients = null;
        String ak74 = "select NUMCLIENT, NOMCLIENT, PRENOMCLIENT" +
                "    from RESERVATION natural join client where NUMVOLPASSAGER = ?";
        PreparedStatement ps = conn.prepareStatement(ak74);
        ps.setInt(1, volNum);
        ResultSet rs = ps.executeQuery();

        clients = makeClientsList(rs);

        return clients;
    }

    public List<Client> getAllClient() throws SQLException {
        List<Client> clients = null;
        String ak74 = "select NUMCLIENT, NOMCLIENT, PRENOMCLIENT from CLIENT";
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(ak74);

        clients = makeClientsList(rs);

        return clients;
    }


    public List<Client> getClientByNumClient(int numClient) throws SQLException {
        List<Client> clients = null;
        String ak74 = "select NUMCLIENT, NOMCLIENT, PRENOMCLIENT" +
                "    from RESERVATION natural join client where NUMVOLPASSAGER = ?";
        PreparedStatement ps = conn.prepareStatement(ak74);
        ps.setInt(1, numClient);
        ResultSet rs = ps.executeQuery();

        clients = makeClientsList(rs);

        return clients;
    }

    private List<Client> makeClientsList(ResultSet rs) throws SQLException {
        List<Client> clients = new ArrayList<>();
        Client cl = null;

        while (rs.next()) {
            cl = mapResultSetToClient(rs);
            clients.add(cl);
        }

        return clients;
    }

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Client v = null;

        if (rs != null) {

            int i = 1;
            int numClient = rs.getInt(i++);
            String nom = rs.getString(i++);
            String prenom = rs.getString(i++);

            v = new Client(numClient, nom, prenom);
        }

        return v;
    }
}
