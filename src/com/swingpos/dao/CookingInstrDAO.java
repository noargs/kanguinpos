package com.swingpos.dao;

import com.swingpos.db.Database;
import com.swingpos.model.CookingInstr;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CookingInstrDAO {
    private static Connection con = null;
    private static Database db = Database.getInstance();

    private List<CookingInstr> cookingInstrs;

    public CookingInstrDAO()
    {
        db.connect();
        con = db.getCon();

        cookingInstrs = new LinkedList<CookingInstr>();
    }

    public Connection getCon()
    {
        return con;
    }

    public List<CookingInstr> getCookingInstrs()
    {
        return Collections.unmodifiableList(cookingInstrs);
    }

    public void addCookingInstr(CookingInstr instr)
    {
        cookingInstrs.add(instr);
    }
    public void load(int categoryId) throws Exception
    {
        cookingInstrs.clear();

        String sql = "select id, cat_id, name, price, visibility, position from cooking_instr where cat_id = ? order by position";
        //Statement selectStatement = con.createStatement();
        PreparedStatement selectStmt = con.prepareStatement(sql);
        selectStmt.setInt(1, categoryId);
        ResultSet results = selectStmt.executeQuery();

        while (results.next())
        {
            int id = results.getInt("id");
            int catId = results.getInt("cat_id");
            String name = results.getString("name");
            BigDecimal price = results.getBigDecimal("price").setScale(2, BigDecimal.ROUND_HALF_UP);
            int visibility = results.getInt("visibility");
            int position = results.getInt("position");

            CookingInstr cookingInstr = new CookingInstr(id, catId, name, price, visibility, position);
            cookingInstrs.add(cookingInstr);
        }
        results.close();
        selectStmt.close();
    }

    public void save() throws SQLException {

        String selectSql = "select count(*) as count from cooking_instr where id=?";
        PreparedStatement selectStmt = con.prepareStatement(selectSql);

        String insertSql = "insert into cooking_instr (id, cat_id, name, price, visibility, position) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

//         String updateSql = "update cooking_instr set cat_id=?, name=?, price=?, visibility=?, position=? where id=?";
//         PreparedStatement updateStatement = con.prepareStatement(updateSql);

        for (CookingInstr cookingInstr : cookingInstrs) {
            int id = cookingInstr.getId();
            int catId = cookingInstr.getCatId();
            String name = cookingInstr.getName();
            BigDecimal price = cookingInstr.getPrice();
            int visibility = cookingInstr.getVisibility();
            int position = cookingInstr.getPosition();

            selectStmt.setInt(1, id);

            ResultSet selectResult = selectStmt.executeQuery();
            selectResult.next();

            int count = selectResult.getInt(1);

            if (count == 0) {
                int col = 1;
                insertStatement.setInt(col++, id);
                insertStatement.setInt(col++, catId);
                insertStatement.setString(col++, name);
                insertStatement.setBigDecimal(col++, price);
                insertStatement.setInt(col++, visibility);
                insertStatement.setInt(col++, position);

                insertStatement.executeUpdate();
            }
//             else {
//                int col = 1;
//                updateStatement.setInt(col++, catId);
//                updateStatement.setString(col++, name);
//                updateStatement.setBigDecimal(col++, price);
//                updateStatement.setInt(col++, visibility);
//                updateStatement.setInt(col++, position);
//                updateStatement.setInt(col++, id);
//
//                updateStatement.executeUpdate();
//            }
        }

//        updateStatement.close();
        insertStatement.close();
        selectStmt.close();
    }


    public void disconnect()
    {
        db.disconnect();
    }

    public void addCookingInstrTable()
    {
        try{
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS cooking_instr "
                    + "(id INTEGER auto_increment NOT NULL, "
                    + " cat_id INTEGER, "
                    + " name VARCHAR(255), "
                    + " price DECIMAL, "
                    + " visibility INTEGER, "
                    + " position INTEGER, "
                    + " PRIMARY KEY ( id ), "
                    + " FOREIGN KEY(cat_id) REFERENCES category(id) "
                    + ") ";
            stmt.executeUpdate(query);
            System.out.println("Table created");

        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
