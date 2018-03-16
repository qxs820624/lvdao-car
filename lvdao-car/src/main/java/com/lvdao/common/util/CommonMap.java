package com.lvdao.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonMap<K, V> {
    private Map<K, ValueHolder<V>> map;

    private final int max_lifecycle;
    private static final int default_max_lifecycle = 1000 * 60 * 5;

    public CommonMap(final int max_lifecycle) {
        map = new ConcurrentHashMap<K, ValueHolder<V>>();
        this.max_lifecycle = max_lifecycle;
        
        Thread clearThread = new Thread(new Runnable() {
        	
            @Override
            public void run() {
                while (true) {
                    final long current = System.currentTimeMillis();
                    final Iterator<Map.Entry<K, ValueHolder<V>>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<K, ValueHolder<V>> entry = iterator.next();
                        if (entry.getValue().timestamp + max_lifecycle < current) {
                            iterator.remove();
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    	//Do nothing
                    }
                }
            }
        });
        clearThread.setDaemon(true);
        clearThread.start();
    }

    public CommonMap() {
        this(default_max_lifecycle);
    }

    public V put(K key, V value) {
        final ValueHolder<V> holder = map.put(key, new ValueHolder<V>(value));
        return holder == null ? value : holder.value;
    }

    public V get(K key) {
        long currentTime = System.currentTimeMillis();
        if (map.containsKey(key)) {
            final ValueHolder<V> holder = map.get(key);
            if (currentTime > max_lifecycle + holder.timestamp) {
                map.remove(key);
                return null;
            } else {
                return holder.value;
            }
        } else {
            return null;
        }
    }

    public V remove(K key) {
        final ValueHolder<V> holder = map.remove(key);
        return holder == null ? null : holder.value;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @SuppressWarnings("hiding")
	private class ValueHolder<V> {
        public V value;
        public long timestamp;

        public ValueHolder(V value) {
            this.value = value;
            this.timestamp = System.currentTimeMillis();
        }
    }
    
}
