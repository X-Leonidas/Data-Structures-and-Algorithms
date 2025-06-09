import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CLHLock implements Lock {

    private final ThreadLocal<CLHLock.Node> node;
    // 尾节点（通过原子引用保证线程安全） 所有线程共享的
    private final AtomicReference<Node> tail = new AtomicReference<>(new CLHLock.Node());

    private static class Node {
        private volatile boolean locked;
    }

    public CLHLock() {
        // withInitial方法延迟初始化，初始值仅在每个线程首次调用 get() 方法时生成，避免不必要的资源开销
        this.node = ThreadLocal.withInitial(CLHLock.Node::new);
    }

    @Override
    public void lock() {
        // Thread local 拿到节点
        final Node node = this.node.get();
        node.locked = true;
         // 拿到以前的tail 节点
        Node pred_node = this.tail.getAndSet(node);
        // 将当前锁放在末尾
        // 自旋，等待加锁
        while (pred_node.locked) {
        }
    }

    @Override
    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        // 重置当前线程的节点，复用节点以减少内存分配开销。
    }

}