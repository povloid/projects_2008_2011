/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package anketa2.renders;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
public class RowHeaderRenderer extends JLabel implements ListCellRenderer
{
    public RowHeaderRenderer(JTable table)
    {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        //setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        //

        //setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1,
        //                    Color.GRAY));

        setBorder( javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        setHorizontalAlignment(LEFT);



        //setForeground(header.getForeground());
        //setBackground(header.getBackground());
        
        //setForeground(table.getGridColor());
        //setBackground(table.getGridColor());

        setFont(header.getFont());
    }

    public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}