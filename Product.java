package data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products", schema = "contac01_prilosh")
public class Product implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "picture", nullable = true, length = 255)
    private String picture;
    @Basic
    @Column(name = "price", nullable = false)
    private int price;
    @Basic
    @Column(name = "mileage", nullable = false)
    private int mileage;
    @Basic
    @Column(name = "petrol", nullable = false, length = 60)
    private String petrol;
    @Basic
    @Column(name = "transmission", nullable = false, length = 60)
    private String transmission;
    @Basic
    @Column(name = "owner_number", nullable = true, length = 20)
    private String ownerNumber;
    @Basic
    @Column(name = "city", nullable = false, length = 60)
    private String city;
    @Basic
    @Column(name = "place", nullable = false, length = 60)
    private String place;
    @Basic
    @Column(name = "date", nullable = false, length = 60)
    private String date;

    public Product() {
    }

    public Product(int id, String name, String picture, int price, int mileage, String petrol, String transmission, String ownerNumber, String city, String place, String date) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.mileage = mileage;
        this.petrol = petrol;
        this.transmission = transmission;
        this.ownerNumber = ownerNumber;
        this.city = city;
        this.place = place;
        this.date = date;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPetrol() {
        return petrol;
    }

    public void setPetrol(String petrol) {
        this.petrol = petrol;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (price != product.price) return false;
        if (mileage != product.mileage) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (picture != null ? !picture.equals(product.picture) : product.picture != null) return false;
        if (petrol != null ? !petrol.equals(product.petrol) : product.petrol != null) return false;
        if (transmission != null ? !transmission.equals(product.transmission) : product.transmission != null)
            return false;
        if (ownerNumber != null ? !ownerNumber.equals(product.ownerNumber) : product.ownerNumber != null) return false;
        if (city != null ? !city.equals(product.city) : product.city != null) return false;
        if (place != null ? !place.equals(product.place) : product.place != null) return false;
        return date != null ? date.equals(product.date) : product.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + mileage;
        result = 31 * result + (petrol != null ? petrol.hashCode() : 0);
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        result = 31 * result + (ownerNumber != null ? ownerNumber.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", petrol='" + petrol + '\'' +
                ", transmission='" + transmission + '\'' +
                ", ownerNumber='" + ownerNumber + '\'' +
                ", city='" + city + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
