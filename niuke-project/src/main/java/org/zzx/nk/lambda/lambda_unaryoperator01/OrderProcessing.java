package org.zzx.nk.lambda.lambda_unaryoperator01;

import java.util.function.UnaryOperator;

/**
 * 案例3：订单处理
 * 问题：使用 UnaryOperator 更新订单状态。
 * 风格：将订单状态从“待处理”更新为“已处理”
 */
public class OrderProcessing {
    public static void main(String[] args) {
        UnaryOperator<Order> processOrder = order -> {
            order.setStatus("Processed");
            return order;
        };

        Order order = new Order(123, "Pending");
        Order processedOrder = processOrder.apply(order);

        System.out.println(processedOrder);
    }

    public void test() {
        Order order = new Order(123, "Pending");
        Order processedOrder = updateOrder(order);
    }

    private Order updateOrder(Order order) {

        return commonFunction(o -> {
            o.setStatus("Processed");
            return o;
        }, order);
    }

    private Order commonFunction(UnaryOperator<Order> o, Order order) {
        return o.apply(order);
    }
}

class Order {
    private int id;
    private String status;

    public Order(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", status='" + status + '\'' +
               '}';
    }
}