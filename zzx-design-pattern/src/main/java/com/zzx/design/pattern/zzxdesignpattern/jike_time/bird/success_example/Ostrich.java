package com.zzx.design.pattern.zzxdesignpattern.jike_time.bird.success_example;

import java.util.ArrayList;

/**
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class Ostrich implements Tweetable, EggLayable {

    private TweetAbility tweetAbility = new TweetAbility();
    private EggLayAbility eggLayAbility = new EggLayAbility();

    @Override
    public void layEgg() {
        //this.eggLayAbility.
    }

    @Override
    public void tweet() {

    }
}
