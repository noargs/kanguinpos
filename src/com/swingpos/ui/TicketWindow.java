package com.swingpos.ui;

import com.swingpos.config.AppConfig;
import com.swingpos.main.PosWindow;
import com.swingpos.swing.RoundedPanel;
import com.swingpos.utils.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class TicketWindow extends JPanel {
    private JPanel posNorth;
    private JPanel posCenterNorth;
    private JPanel posNorthEast;

    private JPanel posEast;
    private JPanel posEastSouth;

    private JPanel posWest;
    private JPanel posMiddle;

    private JLabel lblCustomerLogo;
    private JLabel lblLoginUser;
    private Clock clock;
    private Clock date;
    private Clock day;

    private JButton btnClockIn;
    private JButton btnHome;
    private JButton btnLogOut;



    private Category category;
    private CategoryPageable categoryPage;
    private JPanel pnlCategory;
    private JPanel pnlCategoryPane;
    private JButton btnCategoryPaginationLeft;
    private JButton btnCategoryPaginationRight;
    private JPanel pnlCategoryPagination;
    private JLabel lblCategoryPageInfo;

    private Product product;
    private JPanel pnlProduct;
    private ProductPageable productPage;
    private JButton btnProductPaginationLeft;
    private JButton btnProductPaginationRight;
    private JPanel pnlProductPagination;
    private JLabel lblProductPageInfo;

    private PosOrderTable posOrderTable;
    private OrderTableModel orderTableModel;
    private PosTableListener posTableListener;
    private QuantityListener quantityListener;
    private CookingInstrListener cookingInstrListener;
    private MiscItemListener miscItemListener;
    private ViewListener viewListener;
    private static final int EMPTY_ROW = -1;
    private static final int ZERO_COUNT = 0;
    private JPanel tableControl;
    private JButton btnUpArrow;
    private JButton btnDownArrow;
    private JButton btnSubtract;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnCookingInstruction;
    private JButton btnOrderComments;
    private JButton btnMiscItems;
    private JButton btnHoldOrder;
    private JButton btnUnProcessedOrder;
    private JButton btnCollection;
    private JButton btnOrderHistory;

    private JButton btnOrderTotal;


    private RenderFont renderFont;
    private Font digital;
    private Font comforta;
    private Font iceland;
    private Font icelandB;


    public TicketWindow(Category category, Product product, CategoryPageable catPage, ProductPageable prodPage, OrderTableModel orderTableModel)
    {
        this.category = category;
        this.product = product;
        this.categoryPage = catPage;
        this.productPage = prodPage;
        this.orderTableModel = orderTableModel;

        initFont();
        layoutComponent();
    }

    private void layoutComponent()
    {
        setLayout(new MigLayout("fill, insets 0"));

        getPosDockNorth();

        getPosDockEast();

        getPosDockWest();

        getPosDockCenter();

        add(posEast, "dock east, width 350px");
        add(posNorth, "dock north, height 80");
        add(posWest, "dock west");
        add(posMiddle, "dock center");
    }

    private void initFont()
    {
        renderFont = new RenderFont();

        try {
            digital = renderFont.customFont(AppConfig.getDJBDigitalFont(), Font.PLAIN, 16f);
            comforta = renderFont.customFont(AppConfig.getComfortaBoldFont(), Font.BOLD, 14f);
            iceland = renderFont.customFont(AppConfig.getFontIceland(), Font.BOLD, 40f);
            icelandB = renderFont.customFont(AppConfig.getFontIceland(), Font.PLAIN, 20f);
        }catch(Exception e)
        {

        }
    }

    private void getPosDockNorth() // Application header
    {
        posNorth = new JPanel(new MigLayout("fill, insets 0"));
        posNorth.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY));
        posNorth.setBackground(Color.WHITE);
        posNorth.setOpaque(true);

        lblCustomerLogo = new JLabel();
        lblCustomerLogo.setIcon(IconFactory.getIcon(AppConfig.getImagePath(), AppConfig.getImageCustomerLogo(), new Dimension(135, 80)));
        lblLoginUser = new JLabel();
        lblLoginUser.setText("User: Kebab House");
        lblLoginUser.setForeground(Color.LIGHT_GRAY);
        lblLoginUser.setFont(new Font("courier", Font.BOLD, 12));
        clock = new Clock("time");
        clock.setForeground(Color.GREEN);

        clock.setPreferredSize(new Dimension(82, 0));

        clock.setFont(digital);

        date = new Clock("date");
        date.setForeground(Color.ORANGE);
        date.setFont(new Font(null, Font.PLAIN, 10));

        day = new Clock("day");
        day.setFont(new Font(null, Font.PLAIN, 10));
        day.setForeground(Color.CYAN);

        posNorthEast = new JPanel(new FlowLayout());


        posCenterNorth = new RoundedPanel(new MigLayout("fill, insets 0"), 40, Color.GRAY);
        posCenterNorth.setOpaque(false);
        posCenterNorth.add(lblLoginUser, "cell 0 1 4 1, align left");
        posCenterNorth.add(date, "cell 0 2 4 1, align left");
        posCenterNorth.add(day, "cell 0 3 4 1, align left");
        posCenterNorth.add(clock, "cell 0 4 4 1, align left");

        posCenterNorth.setBorder(new EmptyBorder(new Insets(0, 10, 0, 10)));

        btnClockIn = new JButton();

        btnClockIn.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.CLOCK_IN, new Dimension(30, 30)));
        btnClockIn.setPreferredSize(new Dimension(50, 45));

        posNorthEast.add(btnClockIn);

        btnHome = new JButton();
        btnHome.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.HOME_ICO, new Dimension(40, 40)));
        btnHome.setPreferredSize(new Dimension(50, 45));

        posNorthEast.add(btnHome);

        btnLogOut = new JButton();
        btnLogOut.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.LOG_OUT, new Dimension(30, 30)));
        btnLogOut.setPreferredSize(new Dimension(50, 45));


        posNorthEast.add(btnLogOut);
        posNorthEast.setBackground(Color.WHITE);
        posNorthEast.setOpaque(true);

        posNorth.add(lblCustomerLogo, "align left");
        posNorth.add(posCenterNorth, "align center");
        posNorth.add(posNorthEast, "align right");
    }

    private void getPosDockEast() // Order table and controlls
    {
        posEast = new JPanel(new MigLayout("flowy, insets 0"));
        posOrderTable = new PosOrderTable(orderTableModel);

        tableControl = new JPanel(new MigLayout("fill, insets 2 4 2 0, wrap 1"));
        btnUpArrow = new JButton();
        btnUpArrow.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.UP_ARROW, new Dimension(40, 40)));
        btnUpArrow.setPreferredSize(new Dimension(40, 63));
        tableControl.add(btnUpArrow);


        btnUpArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = posOrderTable.getRowCount();
                int row = posOrderTable.getSelectedRow();

                if(rows > ZERO_COUNT && row == EMPTY_ROW)
                {
                    int startFromBottom = rows - 1;
                    posOrderTable.setRowSelectionInterval(startFromBottom, startFromBottom);

                    posOrderTable.getTable().scrollRectToVisible(new Rectangle(posOrderTable.getTable().getCellRect(row, 0, true)));
                }
                else if(rows > ZERO_COUNT && row > ZERO_COUNT)
                {
                    row--;
                    System.out.println(row);
                    posOrderTable.setRowSelectionInterval(row, row);

                    posOrderTable.getTable().scrollRectToVisible(new Rectangle(posOrderTable.getTable().getCellRect(row, 0, true)));
                }
            }
        });

        btnAdd = new JButton();
        btnAdd.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.PLUS, new Dimension(40, 40)));
        btnAdd.setPreferredSize(new Dimension(40, 60));
        tableControl.add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = posOrderTable.getSelectedRow();
                if(row != EMPTY_ROW && quantityListener != null)
                {
                    quantityListener.increment(row);
                }
            }
        });

        btnDelete = new JButton();
        btnDelete.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.DELETE, new Dimension(40, 40)));
        btnDelete.setPreferredSize(new Dimension(40, 60));
        tableControl.add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = posOrderTable.getSelectedRow();

                if(row != EMPTY_ROW && posTableListener != null)
                {
                    posTableListener.getSelectedRow(row);
                    posOrderTable.fireTableRowsDeleted(row);


                    int rows = posOrderTable.getRowCount();

                    if(rows > ZERO_COUNT)
                    {
                        row--;

                        try{
                            posOrderTable.setRowSelectionInterval(row, row);
                            posOrderTable.getTable().scrollRectToVisible(new Rectangle(posOrderTable.getTable().getCellRect(row, 0, true)));
                        }
                        catch(IllegalArgumentException e1)
                        {
                            row = 0;
                            posOrderTable.setRowSelectionInterval(row, row);
                        }
                    }
                }

            }
        });

        btnSubtract = new JButton();
        btnSubtract.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.MINUS, new Dimension(40, 40)));
        btnSubtract.setPreferredSize(new Dimension(40, 60));
        tableControl.add(btnSubtract);

        btnSubtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = posOrderTable.getSelectedRow();
                if(row != EMPTY_ROW && quantityListener != null)
                {
                    quantityListener.decrement(row);
                }
            }
        });



        btnDownArrow = new JButton();
        btnDownArrow.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.DOWN_ARROW, new Dimension(40, 40)));
        btnDownArrow.setPreferredSize(new Dimension(40, 62));
        tableControl.add(btnDownArrow);

        btnDownArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = posOrderTable.getRowCount();
                int row = posOrderTable.getSelectedRow();
                if(row < rows - 1)
                {
                    row++;
                    System.out.println(row);
                    posOrderTable.setRowSelectionInterval(row, row);
                    posOrderTable.getTable().scrollRectToVisible(new Rectangle(posOrderTable.getTable().getCellRect(row, 0, true)));
                }
            }
        });

        btnCookingInstruction = new JButton();
        btnCookingInstruction.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.COOKING_INSTRUCTION, new Dimension(40, 40)));
        btnCookingInstruction.setPreferredSize(new Dimension(40, 62));
        tableControl.add(btnCookingInstruction);

        btnCookingInstruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = posOrderTable.getSelectedRow();

                if(row != EMPTY_ROW && cookingInstrListener != null)
                {
                    cookingInstrListener.getSelectedRow(row);

                }
            }
        });


        btnMiscItems = new JButton();
        btnMiscItems.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.MISC_ICO, new Dimension(40, 40)));
        btnMiscItems.setPreferredSize(new Dimension(40, 62));
        tableControl.add(btnMiscItems);

        btnMiscItems.addActionListener(e -> {
            if (miscItemListener != null) miscItemListener.openDialog();
        });

        btnOrderComments = new JButton();
        btnOrderComments.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.COMMENT_ICO, new Dimension(40, 40)));
        btnOrderComments.setPreferredSize(new Dimension(40, 62));
        tableControl.add(btnOrderComments);

        posEastSouth = new JPanel(new MigLayout("insets 0, fill"));

        btnOrderTotal = new JButton();
        btnOrderTotal.setFont(iceland);
        btnOrderTotal.setText("€0.00");
        btnOrderTotal.setPreferredSize(new Dimension(281, 50));
        btnOrderTotal.setBackground(new Color(0, 128, 0));
        btnOrderTotal.setForeground(Color.WHITE);
        posEastSouth.add(btnOrderTotal, "cell 0 0 2 1");

        btnOrderTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rows = posOrderTable.getRowCount();
                if(rows != ZERO_COUNT && viewListener != null)
                {
                    viewListener.changeView(AppConfig.PAY_VIEW);
                }
            }
        });

        btnUnProcessedOrder = new JButton("Unprocessed");
        btnUnProcessedOrder.setPreferredSize(new Dimension(138, 50));
        btnUnProcessedOrder.setFont(icelandB);
        posEastSouth.add(btnUnProcessedOrder, "cell 0 1 1 1");

        btnHoldOrder = new JButton("Hold");
        btnHoldOrder.setPreferredSize(new Dimension(138, 50));
        btnHoldOrder.setFont(icelandB);
        posEastSouth.add(btnHoldOrder, "cell 1 1 1 1 ");

        btnCollection = new JButton("Collection");
        btnCollection.setPreferredSize(new Dimension(138, 50));
        btnCollection.setFont(icelandB);
        posEastSouth.add(btnCollection, "cell 0 2 1 1");

        btnOrderHistory = new JButton("History");
        btnOrderHistory.setPreferredSize(new Dimension(138, 50));
        btnOrderHistory.setFont(icelandB);
        posEastSouth.add(btnOrderHistory, "cell 1 2 1 1");




        JPanel posEastPane = new JPanel(new MigLayout("fillx, insets 2 1 2 0"));


        posEastPane.add(posOrderTable, "wrap");
        posEastPane.add(posEastSouth, "growy");


        posEast.add(tableControl, "align right top, wrap");
        posEast.add(posEastPane, "align left top, grow x");
    }

    private void getPosDockWest() // Categories in listing
    {
        pnlCategory = new JPanel(new MigLayout("fill, insets 0, debug"));
        pnlCategoryPagination = new JPanel(new MigLayout("insets 0"));

        btnCategoryPaginationLeft = new JButton();
        btnCategoryPaginationLeft.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.PREVIOUS_SIDE, new Dimension(35, 35)));

        btnCategoryPaginationRight = new JButton();
        btnCategoryPaginationRight.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.NEXT_SIDE, new Dimension(35, 35)));


        category.setCategory(categoryPage);
        lblCategoryPageInfo = new JLabel();
        Utils.paginationInfo(lblCategoryPageInfo, categoryPage.getPage(), categoryPage.getMaxPages());

        btnCategoryPaginationLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(categoryPage.getPreviousPage() < categoryPage.getPage())
                {
                    categoryPage.setPage(categoryPage.getPreviousPage());
                    Utils.paginationInfo(lblCategoryPageInfo, categoryPage.getPage(), categoryPage.getMaxPages());
                    category.setSelectedBtnId(category.getSelectedCategoryId());
                    category.setCategory(categoryPage);




                    // categoryPagination.changePagination(page.getPage());
                }
            }
        });

        btnCategoryPaginationRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(categoryPage.getNextPage() > categoryPage.getPage())
                {
                    categoryPage.setPage(categoryPage.getNextPage());
                    Utils.paginationInfo(lblCategoryPageInfo, categoryPage.getPage(), categoryPage.getMaxPages());
                    category.setSelectedBtnId(category.getSelectedCategoryId());
                    category.setCategory(categoryPage);



                    //categoryPagination.changePagination(page.getPage());

                }
            }
        });

        pnlCategoryPagination.add(btnCategoryPaginationLeft);
        pnlCategoryPagination.add(btnCategoryPaginationRight);

        posWest = new JPanel(new MigLayout("fill, insets 0"));
        TitledBorder titleCategory = new TitledBorder("Category");
        titleCategory.setTitleFont(comforta);
        Border emptyBorder = BorderFactory.createEmptyBorder(8, 0, 2, 0);
        CompoundBorder compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, titleCategory);
        posWest.setBorder(compoundBorder);


        posWest.add(category, "align center top, wrap, pushy");
        posWest.add(lblCategoryPageInfo, "wrap, center");
        posWest.add(pnlCategoryPagination, "align center bottom, height 30");

    }

    private void getPosDockCenter()  // Products related to a category
    {
        pnlProduct = new JPanel(new MigLayout("fillx, insets 0"));
        pnlProductPagination = new JPanel(new MigLayout("fillx, insets 0"));

        btnProductPaginationLeft = new JButton("PREV");
        btnProductPaginationLeft.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.PREVIOUS_SIDE, new Dimension(33, 33)));
        btnProductPaginationLeft.setIconTextGap(5);
        btnProductPaginationLeft.setFont(new Font(null, Font.PLAIN, 16));

        JLabel gap = new JLabel();
        gap.setMinimumSize(new Dimension(20, 40));

        btnProductPaginationRight = new JButton("NEXT");
        btnProductPaginationRight.setIcon(IconFactory.getIcon(AppConfig.getIconPath(), AppConfig.NEXT_SIDE, new Dimension(33, 33)));
        btnProductPaginationRight.setHorizontalTextPosition(SwingConstants.LEFT);
        btnProductPaginationRight.setIconTextGap(5);
        btnProductPaginationRight.setFont(new Font(null, Font.PLAIN, 16));


        product.setProduct(productPage);
        lblProductPageInfo = new JLabel();
        //Utils.paginationInfo(lblProductPageInfo, productPage.getPage(), productPage.getMaxPages());

        btnProductPaginationLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(productPage.getPreviousPage() < productPage.getPage())
                {
                    productPage.setPage(productPage.getPreviousPage());
                    Utils.paginationInfo(lblProductPageInfo, productPage.getPage(), productPage.getMaxPages());
                    product.setProduct(productPage);
                }
            }
        });

        btnProductPaginationRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(productPage.getNextPage() > productPage.getPage())
                {
                    productPage.setPage(productPage.getNextPage());
                    Utils.paginationInfo(lblProductPageInfo, productPage.getPage(), productPage.getMaxPages());
                    product.setProduct(productPage);

                }
            }
        });


        pnlProductPagination.add(btnProductPaginationLeft );
        pnlProductPagination.add(gap);
        pnlProductPagination.add(btnProductPaginationRight );


        posMiddle = new JPanel(new MigLayout("fill, insets 0"));
        TitledBorder titleProduct = new TitledBorder("Product");
        titleProduct.setTitleFont(comforta);
        Border emptyBorderProduct = BorderFactory.createEmptyBorder(8, 0, 2, 0);
        CompoundBorder compoundBorderProduct = BorderFactory.createCompoundBorder(emptyBorderProduct, titleProduct);
        posMiddle.setBorder(compoundBorderProduct);

        posMiddle.add(product, "align center top, wrap, pushy");
        posMiddle.add(lblProductPageInfo, "wrap, center");
        posMiddle.add(pnlProductPagination, "align center bottom");
    }

    public JButton getBtnOrderTotal()
    {
        return btnOrderTotal;
    }

    public void setLblProductPageInfo(int currentPage, int maxPage)
    {
        Utils.paginationInfo(lblProductPageInfo, currentPage, maxPage);
    }

    public void setPosTableListener(PosTableListener listener)
    {
        this.posTableListener = listener;
    }

    public void setQuantityListener(QuantityListener listener)
    {
        this.quantityListener = listener;
    }

    public void setViewListener(ViewListener listener)
    {
        this.viewListener = listener;
    }

    public JTable getTable()
    {
        return posOrderTable.getTable();
    }

    public void setCookingInstrListener(CookingInstrListener listener) {
        this.cookingInstrListener = listener;
    }

    public void setMiscItemListener(MiscItemListener listener) {
        this.miscItemListener = listener;
    }
}
