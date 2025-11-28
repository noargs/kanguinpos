package com.swingpos.dao;

import com.swingpos.db.Database;
import com.swingpos.model.Category;

import java.io.*;
import java.sql.*;
import java.util.*;


public class CategoryDAO {

	private static Connection con = null;
	private static Database db = Database.getInstance();

	private List<Category> categories;
	
	public CategoryDAO()
	{
		db.connect();
		con = db.getCon();

		categories = new LinkedList<Category>();

	}

	public Connection getConnection()
    {
        return con;
    }

	public void configure(String name, String user, String password) throws Exception
	{
		 db.configure(name, user, password);
	}

	public void disconnect()
	{
		db.disconnect();
	}

	public List<Category> getCategories()
	{
		return Collections.unmodifiableList(categories);
	}

	public int getFirstCategoryId()
	{
	 	Iterator iter = getCategories().iterator();
	 	if(iter.hasNext())
		{
		 Category ct = (Category)iter.next();
		 return ct.getId();
		}
	 	return 0;
	}

	public void load() throws Exception
	{
		categories.clear();

		String sql = "select id, name, image, r, g, b, position from category order by position";
		Statement selectStatement = con.createStatement();

		ResultSet results = selectStatement.executeQuery(sql);

		while (results.next())
		{
			int id = results.getInt("id");
			String name = results.getString("name");
			String image = results.getString("image");
			int r = results.getInt("r");
			int g = results.getInt("g");
			int b = results.getInt("b");
			int position = results.getInt("position");

			Category category = new Category(id, name, image, r, g, b, position);
			categories.add(category);
		}
		results.close();
		selectStatement.close();
	}

	public void save() throws SQLException {

		String selectSql = "select count(*) as count from category where id=?";
		PreparedStatement selectStmt = con.prepareStatement(selectSql);

		String insertSql = "insert into category (id, name, image, r, g, b, position) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

//         String updateSql = "update category set name=?, image=?, r=?, g=?, b=?, position=?, where id=?";
//         PreparedStatement updateStatement = con.prepareStatement(updateSql);

		for (Category category : categories) {
			int id = category.getId();
			String name = category.getName();
			String image = category.getImage();
			int r = category.getR();
			int g = category.getG();
			int b = category.getB();
			int position = category.getPosition();

			selectStmt.setInt(1, id);

			ResultSet selectResult = selectStmt.executeQuery();
			selectResult.next();

			int count = selectResult.getInt(1);

			if (count == 0) {
				int col = 1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, image);
				insertStatement.setInt(col++, r);
				insertStatement.setInt(col++, g);
				insertStatement.setInt(col++, b);
				insertStatement.setInt(col++, position);

				insertStatement.executeUpdate();
			}
//             else {
//                int col = 1;
//                updateStatement.setString(col++, name);
//                updateStatement.setString(col++, image);
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

	public void addCategory(Category category)
	{
		categories.add(category);
	}

	public void removeCategory(int index)
	{
		categories.remove(index);
	}

	public void loadFromFile(File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try{
			Category[] category = (Category[]) ois.readObject();

			categories.clear();

			categories.addAll(Arrays.asList(category));

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

		Category[] category = categories.toArray(new Category[categories.size()]);

		oos.writeObject(category);

		oos.close();
	}

//    public void showAllCatgeory()
//    {
//        //TextPanel textPanel = new TextPanel();
//        try{
//            String query = "select * from category";
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()){
//                System.out.println (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
//            }
//        }catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    }

	public void showAllCatgeory()
	{
		//TextPanel textPanel = new TextPanel();
		try{
			String query = "select * from category";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()){
				System.out.println (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7));
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void addCategoryTable()
	{
		try{
			Statement stmt = con.createStatement();
			String query = "CREATE TABLE IF NOT EXISTS category "
					+ "(id INTEGER auto_increment NOT NULL, "
					+ " name VARCHAR(255), "
					+ " image VARCHAR(255), "
					+ " r INTEGER, "
					+ " g INTEGER, "
					+ " b INTEGER, "
					+ " position INTEGER, "
					+ " PRIMARY KEY ( id ))";
			stmt.executeUpdate(query);
			System.out.println("Table created");

		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}




}
