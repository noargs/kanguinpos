package com.swingpos.dao;

import com.swingpos.db.Database;
import com.swingpos.model.Product;
import com.swingpos.model.SubProduct;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SubProductDAO {
    private static Connection con =null;
    private static Database db = Database.getInstance();

    private List<SubProduct> subProducts;

    public SubProductDAO()
    {
        db.connect();
        con = db.getCon();

        subProducts = new LinkedList<SubProduct>();
    }

    public void disconnect(){ db. disconnect(); }
    public static Connection getCon(){return con;}
    public List<SubProduct> getSubProducts(){return Collections.unmodifiableList(subProducts);}
    public void load(int product_id) throws Exception
    {
        subProducts.clear();
        String sql = "select id, product_id, name, image, price, visibility, position from sub_product where product_id = ? order by position";
        PreparedStatement selectSmt = con.prepareStatement(sql);
        selectSmt.setInt(1, product_id);
        ResultSet results = selectSmt.executeQuery();

        while (results.next())
        {
            int id = results.getInt("id");
            int productId = results.getInt("product_id");
            String name = results.getString("name");
            String image = results.getString("image");
            BigDecimal price = results.getBigDecimal("price").setScale(2, BigDecimal.ROUND_HALF_UP);
            int visibility = results.getInt("visibility");
            int position = results.getInt("position");

            SubProduct subProduct = new SubProduct(id, productId, name, image, price, visibility, position);
            subProducts.add(subProduct);
        }

        results.close();
        selectSmt.close();
    }

    public void save() throws SQLException {

        String selectSql = "select count(*) as count from sub_product where id=?";
        PreparedStatement selectStmt = con.prepareStatement(selectSql);

        String insertSql = "insert into sub_product (id, product_id, name, image, price, visibility, position) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

//         String updateSql = "update category set name=?, image=?, r=?, g=?, b=?, position=?, where id=?";
//         PreparedStatement updateStatement = con.prepareStatement(updateSql);

        for (SubProduct subProduct : subProducts) {
            int id = subProduct.getId();
            int productId = subProduct.getProductId();
            String name = subProduct.getName();
            String image = subProduct.getImage();
            BigDecimal price = subProduct.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
            int visibility = subProduct.getVisibility();
            int position = subProduct.getPosition();

            selectStmt.setInt(1, id);

            ResultSet selectResult = selectStmt.executeQuery();
            selectResult.next();

            int count = selectResult.getInt(1);

            if (count == 0) {
                int col = 1;
                insertStatement.setInt(col++, id);
                insertStatement.setInt(col++, productId);
                insertStatement.setString(col++, name);
                insertStatement.setString(col++, image);
                insertStatement.setBigDecimal(col++, price);
                insertStatement.setInt(col++, visibility);
                insertStatement.setInt(col++, position);

                insertStatement.executeUpdate();
            }
        }
        insertStatement.close();
        selectStmt.close();
    }

    public void addSubProduct(SubProduct subProduct)
    {
        subProducts.add(subProduct);
    }

    public void addSubProductTable()
    {
        try{
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS sub_product "
                    + "(id INTEGER auto_increment NOT NULL, "
                    + " product_id INTEGER, "
                    + " name VARCHAR(255), "
                    + " image VARCHAR(255), "
                    + " price DECIMAL, "
                    + " visibility INTEGER, "
                    + " position INTEGER, "
                    + " PRIMARY KEY ( id ), "
                    + " FOREIGN KEY(product_id) REFERENCES product(id) "
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
