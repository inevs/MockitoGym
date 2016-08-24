package de.itagile.mockito.gymnastik;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

import static de.itagile.mockito.gymnastik.IsEven.isEven;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class MockTest {

	List<Integer> list;
	List<Integer> list2;

	@Before
	public void setUp() {
		list = mock(List.class);
		list2 = mock(List.class);
	}

	@Test
	public void timesUsage() throws Exception {
		foo(list);
		verify(list, times(2)).add(anyInt());
	}

	@Test
	public void atLeastOnceUsage() throws Exception {
		foo(list);
		verify(list, atLeastOnce()).add(anyInt());
	}

	@Test
	public void directlyArgumentCheckUsage() throws Exception {
		foo(list);
		verify(list, atLeastOnce()).add(1);
	}

	@Test
	public void ArgumentCaptorUsage() throws Exception {
		foobar(list);
		ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
		verify(list).add(intCaptor.capture());
		assertThat(intCaptor.getValue() % 2, is(0));
	}

	@Test
	public void ArgumentMatcherUsage() throws Exception {
		foobar(list);
		verify(list).add(argThat(isEven()));
	}

	@Test
	public void inOrderUsage() throws Exception {
		InOrder inOrder = inOrder(list, list2);
		bar(list, list2);
		inOrder.verify(list).add(anyInt());
		inOrder.verify(list2).add(anyInt());
	}

	private void foo(List<Integer> list) {
		list.add(1);
		list.add(2);
	}

	private void bar(List<Integer> list, List<Integer> list2) {
		list.add(1);
		list2.add(2);
	}

	private void foobar(List<Integer> list) {
		list.add(2);
	}

}
