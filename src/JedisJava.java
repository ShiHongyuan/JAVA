import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisJava {
    public static void main(String[] args) {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());

        /**** 添加string的键值 *****/
        //set the data in redis string
        jedis.set("tutorial-name", "Redis tutorial");
        // Get the stored data and print it
        System.out.println("Stored string in redis:: "+ jedis.get("tutorial-name"));

        /**** 添加列表的键值 *****/
        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored List in redis:: " + list.get(i));
        }


        /**** 添加集合的键值 *****/
        // Get the stored data and print it
        Set<String> list1 = jedis.keys("*");
        System.out.println("Set of stored keys:: "+list1);
    }
}
