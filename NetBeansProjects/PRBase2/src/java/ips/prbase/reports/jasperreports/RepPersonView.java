/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.prbase.reports.jasperreports;

import ips.prbase.model.attributes.interfaces.IBasicAttribute;
import ips.prbase.model.attributes.interfaces.IDateAttribute;
import ips.prbase.model.attributes.interfaces.IImageAttribute;
import ips.prbase.model.attributes.interfaces.IIntAttribute;
import ips.prbase.model.attributes.interfaces.IRefferenceAttribute;
import ips.prbase.model.attributes.interfaces.ITextAttribute;
import ips.prbase.model.objects.interfaces.impl.Person;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kopychenko
 */
public final class RepPersonView {



    /**
     * Выдать отчет в ответ
     * @param response
     * @param format
     */
    public final static void getReport(HttpServletResponse response) 
            throws Exception {

        // Выходной поток
        ServletOutputStream servletOutputStream = response.getOutputStream();


        // Поднимаем объект
        Person person = new Person(1);

        String [] columnNames = {"block","block_name","caption","vtext","vimage"};

        // Строки
        List<Object[]> rows = new ArrayList<Object[]>();


        // Обрабатываем открытый блок
        for(IBasicAttribute iba: person.getOpenBlock()){
           
            rows.add(createRow(1, "Закрытый блок", iba));
        }

        // Обрабатываем закрытый блок
        for(IBasicAttribute iba: person.getHiddenBlock()){
            rows.add(createRow(2, "Jnrhsnsq блок", iba));
        }

        DefaultTableModel tableModel = new DefaultTableModel((Object[][])rows.toArray(), columnNames);
        
    }



    /**
     * Создать строку
     * @param block
     * @param iba
     * @return
     */
    private static Object[] createRow(int block, String blockName, IBasicAttribute iba){
        Object[] o = new Object[5];

        o[0] = block;
        o[1] = blockName;
        o[2] = iba.getKeyName();

        if( iba instanceof ITextAttribute){
            o[4] = ((ITextAttribute)iba).getText();
        } if(iba instanceof IIntAttribute){

        } if(iba instanceof IDateAttribute){

        } if(iba instanceof IRefferenceAttribute){

        } if(iba instanceof IImageAttribute){
            //o[4] = ((IImageAttribute)iba).get;
        }

        return o;
    }

    
}
