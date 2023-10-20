package org.spring.task;

import javax.validation.constraints.NotNull;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 当线程池满了的时候，线程池阻塞队列
 *
 * @author Chris
 * @version 1.0.0
 * @date 2020/06/21
 */
public class LimitedBlockingQueue<E> extends LinkedBlockingQueue<E> {
    private static final long serialVersionUID = -5748798311137647565L;

    public LimitedBlockingQueue(int maxSize) {
        super(maxSize);
    }

    /** 实现如果线程池满了将阻塞队列 */
    @Override
    public boolean offer(@NotNull E e) {
        // return super.offer(e);
        // turn offer() and add() into a blocking calls (unless interrupted)
        try {
            put(e);
            return true;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return false;
    }
}