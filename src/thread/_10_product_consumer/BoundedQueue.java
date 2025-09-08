package thread._10_product_consumer;

// 큐 인터페이스
public interface BoundedQueue {

    // 데이터를 보관하는 역할
    void put(String data);

    // 보관된 데이터를 가져가는 역할
    String take();
}
