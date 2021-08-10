package ui.panel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

// Represents panel and all components for Time Database Table panel
public class TimeDatabasePanel extends JPanel {
    private Vector<Vector<String>> data;
    private DefaultTableModel tableModel;
    private JTable table;
    private  JScrollPane spTable;

    // MODIFIES: this
    // EFFECTS: constructs a time database panel
    public TimeDatabasePanel(Vector<Vector<String>> data) {
        this.data = data;
        tableModel = new DefaultTableModel(data, getColumns());
        table = new JTable(tableModel);
        spTable = new JScrollPane(table);
        setupTable();
        setupTableColumns();
    }

    // MODIFIES: this
    // EFFECTS: sets up time database panel according to specifications and adds to the panel
    private void setupTable() {
        add(spTable);
        this.setBorder(new EmptyBorder(20, 10, 10, 10));
        spTable.setPreferredSize(new Dimension(1020, 400));
        table.setDefaultEditor(Object.class, null);
    }

    // MODIFIES: this
    // EFFECTS: returns table column headers
    private Vector<String> getColumns() {
        Vector<String> cols = new Vector<>();
        cols.add("Name");
        cols.add("Sex");
        cols.add("Age");
        cols.add("Meet");
        cols.add("Event");
        cols.add("Time");
        return cols;
    }

    // MODIFIES: this, table
    // EFFECTS: sets up default table column widths
    private void setupTableColumns() {
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(25);
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.getColumnModel().getColumn(3).setPreferredWidth(175);
        table.getColumnModel().getColumn(4).setPreferredWidth(75);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    // MODIFIES: this
    // EFFECTS: adds a row data vector
    public void addTime(Vector<String> newTime) {
        data.add(0, newTime);
    }

    // MODIFIES: this
    // EFFECTS: clears rows
    public void clearData() {
        data.clear();
    }

    public JScrollPane getSpTable() {
        return spTable;
    }

    public JTable getTable() {
        return table;
    }

    // MODIFIES: this
    // EFFECTS: returns vector row at index from data
    public Vector<String> getRow(int index) {
        return data.get(index);
    }
}