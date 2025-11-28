package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.model.Order;
import com.swingpos.utils.RenderFont;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class PosOrderTable extends JPanel {
    private JTable table;
    private OrderTableModel tableModel;
    private RenderFont renderFont;
    //private Font pcFont;
    private Font jervinhoRegular;

    public PosOrderTable(OrderTableModel orderTableModel)
    {
        this.tableModel = orderTableModel;
        initLayout();
        initFont();
        initTable();

//        Object[][] data = {
//                {"1", "Doner Kebab Meal", "10.50"},
//                {"", "*no chilli,/n ex lettuce", ""},
//                {"1", "Doner Wrap", "3.50"},
//                {"", "*ex spicy", ""},
//                {"2", "Coke (330 ml)", "2.40"},
//                {"1", "Fanta (330 ml)", "1.20"}
//
//        };








    }

    public JTable getTable()
    {
        return table;
    }

    public int getSelectedRow()
    {
        return table.getSelectedRow();
    }

    public int getRowCount()
    {
        return table.getRowCount();
    }

    public void setRowSelectionInterval(int index0, int index1)
    {
        table.setRowSelectionInterval(index0, index1);
    }

    public void fireTableRowsDeleted(int row)
    {
        tableModel.fireTableRowsDeleted(row, row);

    }

    private void initTable()
    {
        table = new JTable(tableModel){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);

                //  Color row based on a cell value
                if (isRowSelected(row)){ //When A row is selected
                    c.setBackground(Color.GRAY);//Set Background
                    c.setForeground(Color.WHITE);
                }

                if(!isRowSelected(row)){
                    c.setBackground(Color.WHITE);//Set Background
                    c.setForeground(new Color(28, 44, 68));
                }
                return c;
            }
        };


        initTableStyle();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(scrollPane);

    }

    public void refresh()
    {
        tableModel.fireTableDataChanged();
    }

    private void initLayout()
    {
        setLayout(new MigLayout("fill, insets 0"));
    }

    private void initFont()
    {
        renderFont = new RenderFont();


        try {
            //pcFont = renderFont.customFont(AppConfig.getPCFont(), Font.PLAIN, 14f);
            jervinhoRegular = renderFont.customFont(AppConfig.getFontJervinho(), Font.BOLD, 14f);
        }catch(Exception e)
        {
        }
    }

    private void initTableStyle()
    {
        table.setRowHeight(35);
        table.setShowGrid(false);
        //table.setFont(pcFont);
        table.setFont(jervinhoRegular);

        table.setForeground(new Color(28, 44, 68));

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(0);
        columnModel.getColumn(1).setPreferredWidth(170);
        columnModel.getColumn(2).setPreferredWidth(30);
        table.setPreferredScrollableViewportSize(new Dimension(280, 386));
        table.setFillsViewportHeight(true);
    }

}
