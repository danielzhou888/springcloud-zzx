package org.zzx.nk.nk02;

/**
 * 4. 实现一个队列
 * 题目描述：使用数组实现一个简单的队列，包含基本的入队、出队、取队头元素以及判断队列是否为空的操作。
 */
public class Queue {
    // 数组用于存储队列元素
    private int[] arr;
    // 队头指针，指向队头元素
    private int front;
    // 队尾指针，指向队尾元素
    private int rear;
    // 队列的最大容量
    private int capacity;
    // 队列中元素的个数
    private int count;

    // 构造函数，初始化队列
    public Queue(int size) {
        // 初始化数组
        arr = new int[size];
        // 设置队列的最大容量
        capacity = size;
        // 队头指针初始位置为0
        front = 0;
        // 队尾指针初始位置为-1
        rear = -1;
        // 队列初始为空，元素个数为0
        count = 0;
    }

    // 入队操作，将元素x添加到队列末尾
    public void enqueue(int x) {
        // 如果队列已满，抛出异常
        if (isFull()) {
            throw new RuntimeException("Queue overflow");
        }
        // 计算新队尾指针的位置，取模是为了循环使用数组空间
        rear = (rear + 1) % capacity;
        // 将元素x存储到新的队尾位置
        arr[rear] = x;
        // 元素个数加1
        count++;
    }

    // 出队操作，移除并返回队头元素
    public int dequeue() {
        // 如果队列为空，抛出异常
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        // 获取队头元素
        int x = arr[front];
        // 计算新队头指针的位置，取模是为了循环使用数组空间
        front = (front + 1) % capacity;
        // 元素个数减1
        count--;
        // 返回队头元素
        return x;
    }

    // 查看队头元素但不移除
    public int peek() {
        // 如果队列为空，抛出异常
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        // 返回队头元素
        return arr[front];
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        // 如果元素个数为0，则队列为空
        return count == 0;
    }

    // 判断队列是否已满
    public boolean isFull() {
        // 如果元素个数等于队列容量，则队列已满
        return count == capacity;
    }

    // 主函数，测试队列操作
    public static void main(String[] args) {
        // 创建一个容量为5的队列
        Queue queue = new Queue(5);
        // 入队操作，添加元素1, 2, 3到队列
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        // 出队操作，移除并打印队头元素1
        System.out.println(queue.dequeue()); // 1
        // 查看队头元素但不移除，打印元素2
        System.out.println(queue.peek()); // 2
        // 判断队列是否为空，打印false
        System.out.println(queue.isEmpty()); // false
    }
}
