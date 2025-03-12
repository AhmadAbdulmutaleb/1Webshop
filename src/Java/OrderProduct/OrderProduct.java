package Java.OrderProduct;

public class OrderProduct {

    private int orderProductId;
    private int orderId;
    private int ProductId;
    private int quantity;
    private Double unitPrice;


    public OrderProduct(int orderProductId, int orderId, int productId, int quantity, Double unitPrice) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.ProductId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }


    public int getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public Double getTotalaSumman() {
        return quantity * unitPrice;
    }


    @Override
    public String toString() {
        return "ordersProduct{" +
                "orderProductId=" + orderProductId +
                ", orderId=" + orderId +
                ", ProductId=" + ProductId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", Totala Summan=" + getTotalaSumman() +
                '}';
    }
}
