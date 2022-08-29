package domain;

import java.util.stream.Stream;

public enum Rank {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000);

    private final int reward;

    Rank(int reward) {
        this.reward = reward;
    }

    public static Rank valueOf(int matchCount, boolean isHit) {
        return switch (matchCount) {
            case 6 -> Rank.FIRST;
            case 5 -> {
                if (isHit) yield Rank.SECOND;
                else yield Rank.THIRD;
            }
            case 4 -> Rank.FOURTH;
            case 3 -> Rank.FIFTH;
            default -> throw new IllegalStateException("Unexpected value: " + matchCount);
        };
    }

    public int getReward() {
        return reward;
    }

    public static Stream<Rank> stream() {
        return Stream.of(
                Rank.FIRST,
                Rank.SECOND,
                Rank.THIRD,
                Rank.FOURTH,
                Rank.FIFTH
        );
    }
}
