package top.suyiiyii.guavalearn.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RankServiceTest {

    private final List<Map.Entry<String, Double>> list = new ArrayList<>();
    @Autowired
    private RankService rankService;

    @BeforeAll
    void init() {
        rankService.dropRank();
    }


    @Test
    void addScore() {
        // 随机生成 5 个人的分数
        for (int i = 0; i < 5; i++) {
            list.add(Map.entry("user" + i, Math.random() * 100));
        }
        for (var item : list) {
            rankService.addScore(item.getKey(), item.getValue());
        }
    }

    @Test
    void getScore() {
        for (var item : list) {
            double score = rankService.getScore(item.getKey());
            assert score == item.getValue();
            System.out.println(item.getKey() + " : " + score);
        }
    }

    @Test
    void getRank() {
        list.sort((o1, o2) -> (int) (o2.getValue() - o1.getValue()));
        for (var item : list) {
            long rank = rankService.getRank(item.getKey());
            assert rank == list.indexOf(item) + 1;
            System.out.println(item.getKey() + " : " + rank);
        }
    }

    @Test
    void getTopN() {
        int n = 3;
        List<Map.Entry<String, Double>> topN = rankService.getTopN(n);
        for (int i = 0; i < n; i++) {
            assert topN.get(i).getKey().equals(list.get(i).getKey());
            assert topN.get(i).getValue().equals(list.get(i).getValue());
        }
        topN = rankService.getTopN(100);
        assert topN.size() == list.size();

    }
}