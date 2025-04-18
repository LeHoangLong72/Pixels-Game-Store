package dao;

import dto.GameDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class GameDAO implements IDAO<GameDTO, String>{

    /**
     * 
     * @param entity
     * @return 
     */
    @Override
    public boolean create(GameDTO entity) {
        String query = "INSERT INTO tblGames "
                + "(gameID, gameName, developer, genre, price, status)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, entity.getGameID());
            ps.setString(2, entity.getGameName());
            ps.setString(3, entity.getDeveloper());
            ps.setString(4, entity.getGenre());
            ps.setDouble(5, entity.getPrice());
            ps.setBoolean(6, entity.isStatus());
            int i = ps.executeUpdate();
            return i > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * 
     * @return 
     */
    @Override
    public List<GameDTO> readAll() {
        return null;
    }

    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public GameDTO readById(String id) {
        return null;
    }

    /**
     * 
     * @param entity
     * @return 
     */
    @Override
    public boolean update(GameDTO entity) {
        return false;
    }

    /**
     * 
     * @param id
     * @return 
     */
    @Override
    public boolean delete(String id) {
        String sql = "UPDATE tblGames SET status = 0 WHERE gameID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * 
     * @param searchTerm
     * @return 
     */
    @Override
    public List<GameDTO> search(String searchTerm) {
        List<GameDTO> list = new ArrayList<>();
        String query = "SELECT * FROM tblGames WHERE gameName LIKE ? AND status = 1";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                GameDTO game = new GameDTO(
                        rs.getString("gameID"), 
                        rs.getString("gameName"), 
                        rs.getString("developer"), 
                        rs.getString("genre"), 
                        rs.getDouble("price"),
                        rs.getBoolean("status"));
                list.add(game);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
}
