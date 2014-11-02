/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.planer.session;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Параметры выборки
 * @author kopychenko
 */
public class SelectParams {

    private Date bDate;
    private Date eDete;

    public SelectParams() {
        // Формируем даты
//        eDete = new Date((new Date()).getTime() + 1 * (24 * 60 * 60 * 1000));
//        bDate = new Date(eDete.getTime() - 7 * (24 * 60 * 60 * 1000));
//        System.out.println(bDate + "  -  " + eDete);
//
//        GregorianCalendar cal = new GregorianCalendar();


        GregorianCalendar cal2 = new GregorianCalendar();
        int cal2DayOfWeek = cal2.get(Calendar.DAY_OF_WEEK);
        int dDays = cal2DayOfWeek - 3;

        if(dDays <= 0 ){

            dDays += 7;
        }

        System.out.println(dDays);
        System.out.println(dDays * (24 * 60 * 60 * 1000));

        this.bDate = new Date((new Date()).getTime() - dDays * (24 * 60 * 60 * 1000));
        this.eDete = new Date((new Date()).getTime() + 1 * (24 * 60 * 60 * 1000));

    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public Date geteDete() {
        return eDete;
    }

    public void seteDete(Date eDete) {
        this.eDete = eDete;
    }




}
