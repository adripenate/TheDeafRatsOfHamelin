import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeafRatsOfHamelinShould {
    @Test
    public void be_zero_when_they_are_zero() {
        assertThat(DeafRatsOfHamelin.count("P")).isEqualTo(0);
    }

    @Test
    public void be_one_when_it_is_not_facing_hamelin() {
        assertThat(DeafRatsOfHamelin.count("P ~0")).isEqualTo(1);
        assertThat(DeafRatsOfHamelin.count("0~ P")).isEqualTo(1);
    }

    @Test
    public void be_zero_when_it_is_facing_hamelin() {
        assertThat(DeafRatsOfHamelin.count("P 0~")).isEqualTo(0);
        assertThat(DeafRatsOfHamelin.count("~0 P")).isEqualTo(0);
    }

    private static class DeafRatsOfHamelin {
        public static final int NO_DEAF_RATS = 0;

        public static int count(String rats) {
            if (!thereAreAny(rats)) return NO_DEAF_RATS;
            if(isHamelinAtTheFront(rats)){
                return rats.contains("0~") ? NO_DEAF_RATS : 1;
            }else{
                return rats.contains("~0") ? NO_DEAF_RATS : 1;
            }
        }

        private static boolean isHamelinAtTheFront(String rats) {
            return rats.indexOf("P") == 0;
        }

        private static boolean thereAreAny(String rats) {
            return rats.contains("0~") || rats.contains("~0");
        }
    }
}
