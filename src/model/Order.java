package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = -2565570290688784024L;

    private Integer number;
    private Date dateStart;
    private Date dateEnd;
    private String userName;

    public Order(Integer number, Date dateStart, Date dateEnd, String userName) {
        this.number = number;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.userName = userName;
    }

    public Order(){}


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(number, order.number) &&
                Objects.equals(dateStart, order.dateStart) &&
                Objects.equals(dateEnd, order.dateEnd) &&
                Objects.equals(userName, order.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, dateStart, dateEnd, userName);
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", userName=" + userName +
                '}';
    }

}
