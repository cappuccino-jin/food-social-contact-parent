package com.imooc.restaurants.jedis;


import org.hibernate.validator.constraints.Length;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author cappuccino
 * @Date 2022-04-23 12:37
 */
public class JedisTest {

    public void delBigHash(String bigKey) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 游标
        String cursor = "0";
        while (true) {
            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(bigKey, cursor,
                    new ScanParams().count(100));

            // 每次扫描后获取新的游标
            cursor = scanResult.getCursor();    // 获取扫描结果
            List<Map.Entry<String, String>> list = scanResult.getResult();
            if (list == null || list.size() == 0) {
                continue;
            }
            String[] fields = getFieldsFrom(list);  // 删除多个field
            jedis.hdel(bigKey, fields); // 游标为0时停止
            if (cursor.equals("0")) {
                break;
            }
        }
        // 最终删除key
        jedis.del(bigKey);
    }

    /**
     * 获取field数组
     *
     * @param list
     * @return
     */
    private String[] getFieldsFrom(List<Map.Entry<String, String>> list) {
        List<String> fields = new ArrayList<>();
        for (Map.Entry<String, String> entry : list) {
            fields.add(entry.getKey());
        }
        return fields.toArray(new String[fields.size()]);
    }

    public void delBigList(String bigKey) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Long len = jedis.llen(bigKey);
        int counter = 0;
        int left = 100;
        while (counter < len) {
            // 每次从左侧截掉100个
            jedis.ltrim(bigKey, left, len);
            counter += left;

        }
        // 最终删除key
        jedis.del(bigKey);
    }

    public void delBigSet(String bigKey) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 游标
        String cursor = "0";
        while (true) {
            ScanResult<String> scanResult = jedis.sscan(bigKey, cursor, new ScanParams().count(100));
            // 每次扫描后获取新的游标
            cursor = scanResult.getCursor(); // 获取扫描结果
            List<String> list = scanResult.getResult();
            if (list == null || list.size() == 0) {
                continue;
            }
            jedis.srem(bigKey, list.toArray(new String[list.size()]));
            // 游标为0时停止
            if (cursor.equals("0")) {
                break;
            }
        }
        // 最终删除key
        jedis.del(bigKey);
    }

    public void delBigZSet(String bigKey) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 游标
        String cursor = "0";
        while (true) {
            ScanResult<Tuple> zScanResult = jedis.zscan(bigKey, cursor, new ScanParams().count(100));
            // 每次扫描后获取新的游标
            cursor = zScanResult.getCursor(); // 获取扫描结果
            List<Tuple> list = zScanResult.getResult();
            if (list == null || list.size() == 0) {
                continue;
            }
            for (Tuple tuple : list) {
                jedis.zrem(bigKey, tuple.getElement());
            }
            // 游标为0时停止
            if (cursor.equals("0")) {
                break;
            }
        }
        // 最终删除key
        jedis.del(bigKey);
    }

    @Test
    public void delBigString() {
        Jedis jedis = new Jedis("47.93.250.202", 6379);
        jedis.auth("qwe168168");
        jedis.select(1);
        // 游标
        String cursor = "0";
        while (true) {
            ScanResult<String> scanResult = jedis.scan(cursor, new ScanParams().count(1000));
            List<String> list = scanResult.getResult();
            cursor = scanResult.getCursor();
            if (!CollectionUtils.isEmpty(list)) {
                continue;
            }

            for (String key : list) {
                String value = jedis.get(key);
                System.out.printf("key : %s == value : %s", key, value);
            }

            if (cursor.equals("0")) {
                break;
            }
        }
    }

    private static final String HOST = "47.93.250.202";
    private static final int PORT = 6379;

    @Test
    public void scanTest() {
        Jedis jedis = new Jedis(HOST, PORT);
        jedis.auth("qwe168168");
        jedis.select(1);
        ScanResult<String> result = jedis.scan("0");
        String cursor = "";
        boolean finished = false;
        int count = 1;
        while (!finished) {
            List<String> list = result.getResult();
            if (list == null || list.isEmpty()) {
                finished = true;
            }

            for (String s : list) {
                System.out.println(count + ") " + s);
                count++;
            }

            cursor = result.getCursor();
            if (cursor.equalsIgnoreCase("0")) {
                finished = true;
            }
            result = jedis.scan(cursor);

        }
    }


}
