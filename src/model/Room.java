package model;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {

    private static final long serialVersionUID = -2565570290688784024L;

    private Integer id;
    private String type;
    private Integer price;

    public Room(Integer id, String type, Integer price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public Room(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room that = (Room) o;
        return id.equals(that.id) &&
                type.equals(that.type) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
