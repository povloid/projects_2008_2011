/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anketa2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

final class QTableModel extends AbstractTableModel {

    private int id;
    private String question;
    private String description;

    public QTableModel(int id, String question, String description) {
        this.id = id;
        this.question = question;
        this.description = description;
    }

    public QTableModel() {

        // Заголовок
        columns.add("Значение");

        addQRow(new Object[]{1, "Ответ", false, false});
        addQRow(new Object[]{2, "Ответ", false, false});
        addQRow(new Object[]{3, "Ответ", false, false});
        addQRow(new Object[]{4, "Ответ", false, false});
        addQRow(new Object[]{5, "Ответ", false, false});

    }


    private List<String> columns = new ArrayList<String>();

    {
        columns.add("№");
        columns.add("Ответ");
    }

    private ArrayList<ArrayList> rows = new ArrayList<ArrayList>();

    /**
     * Добавить строку
     * @param row
     */
    public void addQRow(Object[] row) {

        ArrayList arow = new ArrayList();
        arow.addAll(Arrays.asList(row));

        rows.add(arow);
    }

    public int getColumnCount() {
        return columns.size();
    }

    public int getRowCount() {
        return rows.size();
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col);
    }

    public Object getValueAt(int row, int col) {
        return rows.get(row).get(col);
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        rows.get(row).set(col, value);
        fireTableCellUpdated(row, col);
    }

    public List<String> getColumns() {
        return columns;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<ArrayList> getRows() {
        return rows;
    }
}
