/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package anketa2.renders;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author kopychenko
 */
public class BoolCheckRender extends JCheckBox
        implements TableCellRenderer {

    Border unselectedBorder = null;
    Border selectedBorder = null;
    Border focusedBorder = null;
    boolean isBordered = true;
    private final static int BORDER_VAL = 2;

    public BoolCheckRender(boolean isBordered) {
        this.isBordered = isBordered;
        setBorderPainted(isBordered);

        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row, int column) {

        if (isBordered) {
            if (isSelected) {

                if (hasFocus) {
                    if (focusedBorder == null) {
                        focusedBorder = BorderFactory.createMatteBorder(BORDER_VAL, BORDER_VAL, BORDER_VAL, BORDER_VAL,
                                Color.BLACK);
                    }
                    setBorder(focusedBorder);

                    setBackground(Color.BLACK);
                    setForeground(Color.WHITE);


                } else {

                    if (selectedBorder == null) {
                        selectedBorder = BorderFactory.createMatteBorder(BORDER_VAL, BORDER_VAL, BORDER_VAL, BORDER_VAL,
                                table.getSelectionBackground());
                    }
                    setBorder(selectedBorder);

                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                }

                

            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(BORDER_VAL, BORDER_VAL, BORDER_VAL, BORDER_VAL,
                            table.getBackground());
                }
                setBorder(unselectedBorder);

                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
        }

        boolean b = ((Boolean) value);

        String s = (row + 1) + ".";
        if (b) {
            s += " Да";
            //setBackground(Color.YELLOW);
        } else {
            //setBackground(table.getBackground());
        }
        setText(s);

        setSelected(b);

        return this;

    }
}
