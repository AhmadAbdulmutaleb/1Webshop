package Java.Order;

import Java.Super.User;

import java.util.Date;


public class Order extends User {

    private Integer orderId;
    private Date order_date;

    public Order(  Integer orderId , int id ,  Date order_date) {
        super(id,null,null); // Här anropar id bara.
        this.orderId = orderId;
        this.order_date = order_date;
    }



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }




    @Override
    public String toString() {
        return "Java.Order{" +

                "order_id= " + orderId +
                ", customerId= " + getId()+'\'' +
                ", order_date= " + order_date + '\'' +
                '}';
    }



}
