package guru.springframework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnotationMockTest {

    @Mock
    Map mapMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMockAnnotation() {
        mapMock.put("key","value");
        assertThat(mapMock.get("key")).isEqualTo("value");

    }
}
