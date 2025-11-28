package com.swingpos.dao;

import com.swingpos.db.Database;
import com.swingpos.model.Order;
import com.swingpos.model.Product;

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ProductDAO {
    private static Connection con = null;
    private static Database db = Database.getInstance();

    private List<Product> products;
    private ArrayList<Product> selectedProduct;

    public ProductDAO()
    {
        db.connect();
        con = db.getCon();

        products = new LinkedList<Product>();
        selectedProduct = new ArrayList<Product>();

    }

    public void disconnect()
    {
        db.disconnect();
    }

    public static Connection getConnection() {
        return con;
    }

    public List<Product> getProducts()
    {
        return Collections.unmodifiableList(products);
    }

    public void load(int categoryId) throws Exception
    {
        products.clear();

        String sql = "select id, cat_id, name, image, price, is_meal, is_offer, meal_price, created_at, r, g, b, position from product where cat_id = ? order by position";
        //Statement selectStatement = con.createStatement();
        PreparedStatement selectStmt = con.prepareStatement(sql);
        selectStmt.setInt(1, categoryId);
        ResultSet results = selectStmt.executeQuery();

        while (results.next())
        {
            int id = results.getInt("id");
            int catId = results.getInt("cat_id");
            String name = results.getString("name");
            String image = results.getString("image");
            BigDecimal price = results.getBigDecimal("price").setScale(2, BigDecimal.ROUND_HALF_UP);
            int isMeal = results.getInt("is_meal");
            int isOffer = results.getInt("is_offer");
            BigDecimal mealPrice = results.getBigDecimal("meal_price").setScale(2, BigDecimal.ROUND_HALF_UP);
            Date createdAt = results.getDate("created_at");
            int r = results.getInt("r");
            int g = results.getInt("g");
            int b = results.getInt("b");
            int position = results.getInt("position");

            Product product = new Product(id, catId, name, image, price, isMeal, isOffer, mealPrice, createdAt, r, g, b, position);
            products.add(product);
        }
        results.close();
        selectStmt.close();
    }

    public void save() throws SQLException {

        String selectSql = "select count(*) as count from product where id=?";
        PreparedStatement selectStmt = con.prepareStatement(selectSql);

        String insertSql = "insert into product (id, cat_id, name, image, price, is_meal, is_offer, meal_price, created_at, r, g, b, position) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = con.prepareStatement(insertSql);

//         String updateSql = "update category set cat_id=?, name=?, image=?, price=?, is_meal=?, is_offer=?, meal_price=?, created_at=?, r=?, g=?, b=?, position=? where id=?";
//         PreparedStatement updateStatement = con.prepareStatement(updateSql);

        for (Product product : products) {
            int id = product.getId();
            int catId = product.getCatId();
            String name = product.getName();
            String image = product.getImage();
            BigDecimal price = product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
            int isMeal = product.getIsMeal();
            int isOffer = product.getIsOffer();
            BigDecimal mealPrice = product.getMealPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
            Date createdAt = product.getCreatedAt();
            int r = product.getR();
            int g = product.getG();
            int b = product.getB();
            int position = product.getPosition();

            selectStmt.setInt(1, id);

            ResultSet selectResult = selectStmt.executeQuery();
            selectResult.next();

            int count = selectResult.getInt(1);

            if (count == 0) {
                int col = 1;
                insertStatement.setInt(col++, id);
                insertStatement.setInt(col++, catId);
                insertStatement.setString(col++, name);
                insertStatement.setString(col++, image);
                insertStatement.setBigDecimal(col++, price);
                insertStatement.setInt(col++, isMeal);
                insertStatement.setInt(col++, isOffer);
                insertStatement.setBigDecimal(col++, mealPrice);
                insertStatement.setDate(col++, createdAt);
                insertStatement.setInt(col++, r);
                insertStatement.setInt(col++, g);
                insertStatement.setInt(col++, b);
                insertStatement.setInt(col++, position);

                insertStatement.executeUpdate();
            }
//             else {
//                int col = 1;
//                updateStatement.setInt(col++, catId);
//                updateStatement.setString(col++, name);
//                updateStatement.setString(col++, image);
//                updateStatement.setBigDecimal(col++, price);
//                updateStatement.setInt(col++, isMeal);
//                updateStatement.setInt(col++, isOffer);
//                updateStatement.setBigDecimal(col++, mealPrice);
//                updateStatement.setDate(col++, createdAt);
//                updateStatement.setInt(col++, r);
//                updateStatement.setInt(col++, g);
//                updateStatement.setInt(col++, b);
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

    public void addProduct(Product product)
    {
        products.add(product);
    }

    public ArrayList<Product> getSelectedProduct() {
        return selectedProduct;
    }

    public int getSelectedProductId()
    {
        Iterator iterator = selectedProduct.iterator();
        Product product = (Product) iterator.next();
        return product.getId();
    }

    public int getOrderCatId(int productId)
    {
        for(Product product: products)
        {
            if(product.getId() == productId)
            {
                return product.getCatId();
            }
        }
        return 0;
    }

    public void setSelectedProduct(int productId)
    {
        for(Product product: products)
        {
            if(product.getId() == productId)
            {
                selectedProduct.clear();
                int id = product.getId();
                int catId = product.getCatId();
                String name = product.getName();
                String image = product.getImage();
                BigDecimal price = product.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
                int isMeal = product.getIsMeal();
                int isOffer = product.getIsOffer();
                BigDecimal mealPrice = product.getMealPrice().setScale(2, BigDecimal.ROUND_HALF_UP);
                Date createdAt = product.getCreatedAt();
                int r = product.getR();
                int g = product.getG();
                int b = product.getB();
                int position = product.getPosition();
                Product prd = new Product(id, catId, name, image, price, isMeal, isOffer, mealPrice, createdAt, r, g, b, position);
                selectedProduct.add(prd);
            }
        }
    }


    public boolean isMeal()
    {
        Iterator iterator = getSelectedProduct().iterator();

        while(iterator.hasNext())
        {
            Product product = (Product)iterator.next();
            if(product.getMealPrice().compareTo(BigDecimal.ZERO) != 0)
            {
                return true;
            }
        }
        return false;
    }


    public boolean hasMainPrice()
    {
        Iterator iterator = getSelectedProduct().iterator();
        Product product = (Product)iterator.next();
        if(product.getPrice().compareTo(BigDecimal.ZERO) != 0)
        {
            return true;
        }
        return false;
    }





    public void removeProduct(int index)
    {
        products.remove(index);
    }

    public void loadFromFile(File file) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try{
            Product[] product = (Product[]) ois.readObject();

            products.clear();

            products.addAll(Arrays.asList(product));

        }catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        ois.close();
    }

    public void saveToFile(File file) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Product[] product = products.toArray(new Product[products.size()]);

        oos.writeObject(product);

        oos.close();
    }

    public void showAllProducts()
    {
        //TextPanel textPanel = new TextPanel();
        try{
            String query = "select * from product";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                System.out.println (rs.getInt(1) + " " + rs.getInt(2)
                            + " " + rs.getString(3) + " " + rs.getString(4)
                            + " " + rs.getBigDecimal(5) + " " + rs.getInt(6)
                            + " " + rs.getInt(7) + " " + rs.getBigDecimal(8)
                            + " " + rs.getDate(9) + " " + rs.getInt(10)
                            + " " + rs.getInt(11) + " " + rs.getInt(12)
                            + " " + rs.getInt(13));
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void addProductTable()
    {
        try{
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS product "
                    + "(id INTEGER auto_increment NOT NULL, "
                    + " cat_id INTEGER, "
                    + " name VARCHAR(255), "
                    + " image VARCHAR(255), "
                    + " price DECIMAL, "
                    + " is_meal INTEGER, "
                    + " is_offer INTEGER, "
                    + " meal_price DECIMAL, "
                    + " created_at DATE, "
                    + " r INTEGER, "
                    + " g INTEGER, "
                    + " b INTEGER, "
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
