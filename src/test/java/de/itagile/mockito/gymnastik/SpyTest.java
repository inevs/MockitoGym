package de.itagile.mockito.gymnastik;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SpyTest {

	private Helper helper;
	private Helper spy;

	@Before
	public void setUp() throws Exception {
		helper = new Helper();
		spy = spy(helper);
	}

	@Test
	public void mocksOnlyPartialClasses() {
		List<Integer> list = new ArrayList();
		List<Integer> spy = spy(list);
		when(spy.size()).thenReturn(42);

		spy.add(5);
		spy.add(4);

		assertThat(spy.get(0), is(5));
		assertThat(spy.get(1), is(4));

		assertThat(spy.size(), is(42));
	}

	@Test
	public void mocksInternalMethodCalls() {
		doReturn(10).when(spy).getValuesFromNetwork();

		assertThat(spy.computeSomeValues(), is(420));
	}

	@Test
	public void verifiesInternalMethodCalls() {
		doReturn(10).when(spy).getValuesFromNetwork();

		assertThat(spy.computeSomeValues(), is(420));

		verify(spy).configureSomeStuff();
	}

	@Test
	public void imposeMethodsThatShouldNotBeUsedInTest() {
		doNothing().when(spy).makeSomeBasicSettings();
		assertThat(spy.configureDatabase(), is(42));
	}
	


}

