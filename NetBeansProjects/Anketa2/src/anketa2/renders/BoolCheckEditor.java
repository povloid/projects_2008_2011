/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anketa2.renders;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author kopychenko
 */
public class BoolCheckEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private JCheckBox checkBox;
    protected static final String EDIT = "edit";

    public BoolCheckEditor() {

        checkBox = new JCheckBox();


        checkBox.setActionCommand(EDIT);
        checkBox.addActionListener(this);
        checkBox.setBorderPainted(true);

    }

    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }

    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {

        checkBox.setSelected((Boolean) value);
        return this.checkBox;

    }

    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            fireEditingStopped();
        } else {
        }
    }
}
