/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package example2.p2;

/**
 *
 * @author traveler
 */
public class Customer {
    
    private int id;
    private String name;
    private String country;
    private String city;
    private double creditLimit;

    public Customer() {
    }

    public Customer(int id, String name, String country, String city, double creditLimit) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.creditLimit = creditLimit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
