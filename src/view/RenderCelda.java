/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


class RenderCelda extends DefaultTableCellRenderer
{ 
   @Override
    public Component getTableCellRendererComponent(JTable jTable1,Object value,boolean selected, boolean focused, int row, int column)
    {
        super.getTableCellRendererComponent(jTable1, value, selected, focused, row, column);
        if ("0".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }else if ("1".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.gray);
            this.setForeground(Color.BLACK);
        }else if ("2".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.GREEN);
            this.setForeground(Color.BLACK);
        }else if ("3".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.magenta);
            this.setForeground(Color.BLACK);
        }else if ("4".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.BLUE);
            this.setForeground(Color.BLACK);
        }else if ("5".equals((jTable1.getValueAt(row,column))))
        {
            this.setOpaque(true);
            this.setBackground(Color.CYAN);
            this.setForeground(Color.BLACK);
        }else{
           if ("6".equals((jTable1.getValueAt(row,column)))) 
            {
                this.setOpaque(true);
                this.setBackground(Color.yellow);
                this.setForeground(Color.BLACK);
            }else if ("R".equals((jTable1.getValueAt(row,column))))
            {
            this.setOpaque(true);
            this.setBackground(Color.RED);
            this.setForeground(Color.BLACK);
            }
        }
    return this;
    }
}





