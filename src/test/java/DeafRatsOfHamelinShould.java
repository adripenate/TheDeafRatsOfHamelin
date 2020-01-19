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

    @Test
    public void be_two_when_they_are_facing_right_and_hamlet_is_in_middle() {
        assertThat(DeafRatsOfHamelin.count("~0~0P~0~0")).isEqualTo(2);
    }

    private static class DeafRatsOfHamelin {
        public static final int NO_DEAF_RATS = 0;
        public static final String HAMLET = "P";
        public static final String RAT_FACING_RIGHT = "~0";
        private static final String RAT_FACING_LEFT = "0~";

        public static int count(String rats) {
            if (!thereAreAny(rats)) return NO_DEAF_RATS;
            rats = normalizeSpaces(rats);
            if(isHamelinAtTheFront(rats)){
                return countDeafRats(rats, RAT_FACING_RIGHT);
            }else if (isHamelinAtTheEnd(rats)){
                return countDeafRats(rats, RAT_FACING_LEFT);
            }else{
                int hamelinPosition = searchHamelin(rats);
                return countDeafRats(rats, hamelinPosition);
            }
        }

        private static int countDeafRats(String rats, int hamelinPosition) {
            return countDeafRats(rats.substring(hamelinPosition), RAT_FACING_RIGHT)
                    + countDeafRats(rats.substring(0, hamelinPosition+1), RAT_FACING_LEFT);
        }

        private static boolean isHamelinAtTheEnd(String rats) {
            return searchHamelin(rats) == rats.length()-1;
        }

        private static String normalizeSpaces(String rats) {
            return rats.replace(" ", "");
        }

        private static boolean isHamelinAtTheFront(String rats) {
            return searchHamelin(rats) == 0;
        }

        private static int searchHamelin(String rats) {
            return rats.indexOf(HAMLET);
        }

        private static int countDeafRats(String rats, String deafRat){
            int deafRats = 0;
            while (hamletIsNotAlone(rats)){
                String rat = getFirstRat(rats, deafRat);
                if (ratIsPayingAttention(deafRat, rat)) deafRats++;
                rats = rats.replaceFirst(rat, "");
            }
            return deafRats;
        }

        private static boolean ratIsPayingAttention(String deafRat, String rat) {
            return rat.equals(deafRat);
        }

        private static String getFirstRat(String rats, String deafRat) {
            return (deafRat.equals(RAT_FACING_RIGHT))? rats.substring(1, 3) : rats.substring(0, 2);
        }

        private static boolean hamletIsNotAlone(String rats) {
            return !rats.equals(HAMLET);
        }

        private static boolean thereAreAny(String rats) {
            return rats.contains(RAT_FACING_LEFT) || rats.contains(RAT_FACING_RIGHT);
        }
    }
}
