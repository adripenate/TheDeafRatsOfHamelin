import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeafRatsOfHamelinShould {
    @Test
    public void be_zero_when_they_are_zero() {
        assertThat(DeafRatsOfHamelin.count("P")).isEqualTo(0);
    }

    @Test
    public void be_one_when_it_is_facing_right() {
        assertThat(DeafRatsOfHamelin.count("P ~0")).isEqualTo(1);
    }

    @Test
    public void be_zero_when_it_is_facing_left() {
        assertThat(DeafRatsOfHamelin.count("P 0~")).isEqualTo(0);
    }

    @Test
    public void be_zero_when_it_is_facing_right() {
        assertThat(DeafRatsOfHamelin.count("~0 P")).isEqualTo(0);
    }


    private static class DeafRatsOfHamelin {
        public static int count(String rats) {
            if (!thereAreAny(rats)) return 0;
            if(rats.indexOf("P") == 0){
                if (rats.contains("0~")) return 0;
                return 1;
            }else{
                return 0;
            }
        }

        private static boolean thereAreAny(String rats) {
            return rats.contains("0~") || rats.contains("~0");
        }
    }
}
