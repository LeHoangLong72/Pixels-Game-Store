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

    @Override
    public boolean create(GameDTO entity) {
        return false;
    }

    @Override
    public List<GameDTO> readAll() {
        return null;
    }

    @Override
    public GameDTO readById(String id) {
        return null;
    }

    @Override
    public boolean update(GameDTO entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<GameDTO> search(String searchTerm) {
        List<GameDTO> list = new ArrayList<>();
        String query = "SELECT * FROM tblGames WHERE gameName LIKE ?";
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
                        rs.getDouble("price"));
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
