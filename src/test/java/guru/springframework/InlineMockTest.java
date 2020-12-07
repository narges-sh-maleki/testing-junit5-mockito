package guru.springframework;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class InlineMockTest {
    @Test
    void testInlineMock() {
        Map map = mock(Map.class);
        assertThat(map.size()).isEqualTo(0);
    }
}
