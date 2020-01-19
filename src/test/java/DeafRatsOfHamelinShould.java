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

    @Test
    public void be_two_when_they_are_not_facing_hamelin() {
        assertThat(DeafRatsOfHamelin.count("P ~0~0")).isEqualTo(2);
        assertThat(DeafRatsOfHamelin.count("0~ 0~P")).isEqualTo(2);
    }

    @Test
    public void be_zero_when_they_are_facing_hamelin() {
        assertThat(DeafRatsOfHamelin.count("P 0~0~")).isEqualTo(0);
    }

    private static class DeafRatsOfHamelin {
        public static final int NO_DEAF_RATS = 0;
        public static final String HAMLET = "P";
        public static final String RAT_FACING_RIGHT = "~0";
        private static final String RAT_FACING_LEFT = "0~";

        public static int count(String rats) {
            if (!thereAreAny(rats)) return NO_DEAF_RATS;
            if(isHamelinAtTheFront(rats)){
                return countDeafRats(rats.replace(" ", ""), RAT_FACING_RIGHT);
            }else{
                return countDeafRats(rats.replace(" ", ""), RAT_FACING_LEFT);
            }
        }

        private static boolean isHamelinAtTheFront(String rats) {
            return rats.indexOf(HAMLET) == 0;
        }

        private static int countDeafRats(String rats, String deafRat){
            int deafRats = 0;
            while (!rats.equals(HAMLET)){
                String rat = (deafRat.equals(RAT_FACING_RIGHT))? rats.substring(1, 3) : rats.substring(0, 2);
                if (rat.equals(deafRat)) deafRats++;
                rats = rats.replaceFirst(rat, "");
            }
            return deafRats;
        }

        private static boolean thereAreAny(String rats) {
            return rats.contains("0~") || rats.contains(RAT_FACING_RIGHT);
        }
    }
}
