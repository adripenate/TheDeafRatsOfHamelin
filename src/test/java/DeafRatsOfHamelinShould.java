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
    public void be_two_when_some_are_not_facing_hamlet_at_the_middle() {
        assertThat(DeafRatsOfHamelin.count("~0~0P~0~0")).isEqualTo(2);
    }
}
