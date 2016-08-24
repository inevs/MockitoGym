package de.itagile.mockito.gymnastik;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StubTest {

	private List<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = mock(List.class);
	}

	@Test
	public void definesReturnValue() {
		when(list.get(0)).thenReturn(42);
		assertThat(list.get(0), is(42));
	}

	@Test
	public void ignoresParameter() {
		when(list.get(anyInt())).thenReturn(42);
		assertThat(list.get(100), is(42));
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionWhenCalled() {
		when(list.get(1)).thenThrow(IllegalArgumentException.class);
		list.get(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwExceptionOnVoidMethod() {
		doThrow(IllegalArgumentException.class).when(list).add(42);
		list.add(42);
	}


}
