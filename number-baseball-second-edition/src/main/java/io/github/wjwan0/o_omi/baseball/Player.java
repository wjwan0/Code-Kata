package io.github.wjwan0.o_omi.baseball;

import java.util.List;

public interface Player {

    List<Ball> getBalls();

    static Player computer() {
        return new Computer();
    }

    static Player user(int[] ballsArray) {
        return new User(ballsArray);
    }
}