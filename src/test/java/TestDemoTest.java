import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {

	private TestDemo testDemo = null;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a,  b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
		return Stream.of(
			arguments(5, 7, 12, false),
			arguments(-5, 2, -3, true),
			arguments(0, 7, 7, true),
			arguments(5, 0, 5, true),
			arguments(13, 26, 39, false)
		);
		// @formatter:on
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(7).when(mockDemo).getRandomInt();
		int sevenSquared = mockDemo.randomNumberSquared();
		assertThat(sevenSquared).isEqualTo(49);
	}

}