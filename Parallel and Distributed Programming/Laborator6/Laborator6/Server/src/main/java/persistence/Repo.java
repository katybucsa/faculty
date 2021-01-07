package persistence;

import model.Bilet;
import model.Categorie;
import model.Loc;
import model.Spectacol;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created on: 19-Dec-19, 21:55
 *
 * @author: Katy Bucșa
 */

public class Repo {

    private DBConnection dbConnection;

    public Repo(Properties properties) {

        dbConnection = new DBConnection(properties);
    }

    public synchronized Spectacol getSpectacolById(int id) {

        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from Spectacole where id=?")) {
            stat.setInt(1, id);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                Date data = new Date(resultSet.getLong("data"));
                String titlu = resultSet.getString("titlu");
                String descriere = resultSet.getString("descriere");
                Spectacol s = new Spectacol(id, data, titlu, descriere);
                return s;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Spectacol couldn't be found in the database!");
        }
    }

    public synchronized Loc getLocByIdSpectacolAndIdCategorie(int idS, int idC) {

        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("select * from Locuri where id_spectacol=? and id_categorie=?")) {
            stat.setInt(1, idS);
            stat.setInt(2, idC);
            try (ResultSet resultSet = stat.executeQuery()) {
                resultSet.next();
                int idSpectacol = resultSet.getInt("id_spectacol");
                int idCategorie = resultSet.getInt("id_categorie");
                int loc_nr = resultSet.getInt("nr_locuri");
                int libere = resultSet.getInt("locuri_libere");
                Loc loc = new Loc(idSpectacol, idCategorie, loc_nr, libere);
                return loc;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Loc" + "couldn't be found in the database!");
        }
    }

    public synchronized Iterable<Loc> findAllLocuri() {

        Connection conn = dbConnection.getConnection();
        List<Loc> locuri = new ArrayList<>();
        try (PreparedStatement stat = conn.prepareStatement("select * from Locuri")) {
            try (ResultSet resultSet = stat.executeQuery()) {
                while (resultSet.next()) {
                    int idSpectacol = resultSet.getInt("id_spectacol");
                    int idCategorie = resultSet.getInt("id_categorie");
                    int loc_nr = resultSet.getInt("nr_locuri");
                    int libere = resultSet.getInt("locuri_libere");
                    Loc loc = new Loc(idSpectacol, idCategorie, loc_nr, libere);
                    locuri.add(loc);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Spectacol couldn't be found in the database!");
        }
        return locuri;
    }

    public synchronized Iterable<Categorie> findAllCategorii() {

        Connection conn = dbConnection.getConnection();
        List<Categorie> categorii = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement("select * from Categorii")) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int pret = resultSet.getInt("pret");
                    Categorie c = new Categorie(id, pret);
                    categorii.add(c);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorii;
    }

    public synchronized Iterable<Bilet> findAllBilete() {

        Connection conn = dbConnection.getConnection();
        List<Bilet> bilete = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement("select * from Bilete")) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int idSpectacol = resultSet.getInt("id_spectacol");
                    int idCategorie = resultSet.getInt("id_categorie");
                    int numar = resultSet.getInt("numar");
                    Date dataVanzare = new Date(resultSet.getLong("data_vanzare"));
                    Spectacol s = getSpectacolById(idSpectacol);
                    Bilet b = new Bilet(id, s, idCategorie, numar, dataVanzare);
                    bilete.add(b);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilete;
    }


    public synchronized Bilet saveTicket(Bilet b) {
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("INSERT INTO Bilete (id_spectacol,id_categorie,numar,data_vanzare) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stat.setInt(1, b.getSpectacol().getId());
            stat.setInt(2, b.getIdCategorie());
            stat.setInt(3, b.getNumar());
            stat.setLong(4, b.getDataVanzare().toInstant().toEpochMilli());
            stat.executeUpdate();
            try (ResultSet generatedKeys = stat.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    b.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error " + e);
        }
        Loc l = getLocByIdSpectacolAndIdCategorie(b.getSpectacol().getId(), b.getIdCategorie());
        l.setNrLocuriLibere(l.getNrLocuriLibere() - 1);
        updateLoc(l);
        return b;
    }

    private synchronized void updateLoc(Loc l) {

        Connection conn = dbConnection.getConnection();
        try (PreparedStatement stat = conn.prepareStatement("UPDATE Locuri SET locuri_libere=? WHERE id_spectacol=? and id_categorie=?")) {
            stat.setInt(1, l.getNrLocuriLibere());
            stat.setInt(2, l.getIdSpectacol());
            stat.setInt(3, l.getIdCategorie());
            stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error " + e);
        }
    }
}
