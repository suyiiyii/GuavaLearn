package top.suyiiyii.guavalearn.service;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class RankService {

    RedissonClient redissonClient;
    RScoredSortedSet<String> rank;

    public RankService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
        rank = redissonClient.getScoredSortedSet("rank");
    }

    /**
     * 添加一个排名
     *
     * @param username 用户名
     * @param score    分数
     */
    public void addScore(String username, double score) {
        rank.add(score, username);
    }

    /**
     * 获取用户的分数
     *
     * @param username 用户名
     * @return 分数
     */
    public double getScore(String username) {
        return rank.getScore(username);
    }

    /**
     * 查询用户的排名
     *
     * @param username
     * @return
     */
    public long getRank(String username) {
        return rank.revRank(username) + 1;
    }

    /**
     * 删除用户的排名
     *
     * @param username 用户名
     */
    public void dropRank(String username) {
        rank.remove(username);
    }


    /**
     * 清空所有排名
     */
    public void dropRank() {
        rank.delete();
    }

    /**
     * 获取排名前N的用户和对应的分数
     *
     * @param n 前N名
     * @return
     */
    public List<Map.Entry<String, Double>> getTopN(int n) {
        Collection<ScoredEntry<String>> entries = rank.entryRangeReversed(0, n - 1);

        List<Map.Entry<String, Double>> list =
                entries.stream()
                        .map(entry -> Map.entry(entry.getValue(), entry.getScore()))
                        .toList();
        return list;
    }


}
