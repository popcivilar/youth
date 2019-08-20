package com.popcivilar.youth.general;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KnowledgeServiceApplicationTests {


    @Autowired
    private StringRedisTemplate redisTemplate;

    public static final String SCORE_RANK = "ranking-list";

    @Test
    public void redicTest(){
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();

        stringStringZSetOperations.add(SCORE_RANK,"article:00001",2);
        stringStringZSetOperations.add(SCORE_RANK,"article:00002",5);
        stringStringZSetOperations.add(SCORE_RANK,"article:00003",3);
        stringStringZSetOperations.add(SCORE_RANK,"article:00004",4);
        stringStringZSetOperations.add(SCORE_RANK,"article:00005",1);
        printZSet(SCORE_RANK);
    }


    private void printZSet(String key) {
        //按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
        Set<String> range = redisTemplate.opsForZSet().range(key, 0, -1);
        //reverseRange 从大到小
        System.out.println(range);
    }

    @Test
    public void test5() {
        //返回集合内元素的排名，以及分数（从小到大）
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().rangeWithScores("ranking-list", 0, -1);
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            System.out.println(tuple.getValue() + " : " + tuple.getScore());
        }
    }
}
