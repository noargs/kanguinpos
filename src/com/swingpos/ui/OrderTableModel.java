package com.swingpos.ui;

import com.swingpos.model.Order;
import com.swingpos.model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

	private List<Order> db;

	private String[] colNames = {"Qty", "Product", "€"};

	public OrderTableModel()
	{
		//db = new ArrayList<>();
		this.db = db;
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	@Override
	public int getRowCount() {
		if(db != null)
		{
			return db.size();
		}
		return 0;

	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	public void setData(List<Order> db)
	{
		this.db = db;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { 
		Order order = db.get(rowIndex);

		switch(columnIndex)
		{
			case 0:
				return order.getQty();
			case 1:
				return order.getItemName();
			case 2:
				return order.getItemPrice();

		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}


}
