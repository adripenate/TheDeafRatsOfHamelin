import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DinglemouseShould {
    @Test
    public void be_zero_when_they_are_zero() {
        assertThat(Dinglemouse.countDeafRats("P")).isEqualTo(0);
    }

    @Test
    public void be_one_when_it_is_not_facing_hamelin() {
        assertThat(Dinglemouse.countDeafRats("P ~O")).isEqualTo(1);
        assertThat(Dinglemouse.countDeafRats("O~ P")).isEqualTo(1);
    }

    @Test
    public void be_zero_when_it_is_facing_hamelin() {
        assertThat(Dinglemouse.countDeafRats("P O~")).isEqualTo(0);
        assertThat(Dinglemouse.countDeafRats("~O P")).isEqualTo(0);
    }

    @Test
    public void be_two_when_they_are_not_facing_hamelin() {
        assertThat(Dinglemouse.countDeafRats("P ~O~O")).isEqualTo(2);
        assertThat(Dinglemouse.countDeafRats("O~ O~P")).isEqualTo(2);
    }

    @Test
    public void be_zero_when_they_are_facing_hamelin() {
        assertThat(Dinglemouse.countDeafRats("P O~O~")).isEqualTo(0);
    }

    @Test
    public void be_two_when_some_are_not_facing_hamlet_at_the_middle() {
        assertThat(Dinglemouse.countDeafRats("~O~OP~O~O")).isEqualTo(2);
    }
}
