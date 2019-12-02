import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeafRatsOfHamelinShould {
    @Test
    public void be_zero_when_they_are_zero() {
        assertThat(DeafRatsOfHamelin.countDeafRats("P")).isEqualTo(0);
    }

    private static class DeafRatsOfHamelin {
        public static int countDeafRats(String p) {
            return 0;
        }
    }
}
