package de.itagile.mockito.gymnastik;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockTest {

	@Mock
	List<Integer> list;
	@Mock
	List<Integer> list2;

	@Test
	public void timesUsage() throws Exception {
		foo(list);
		verify(list, Mockito.times(2)).add(Mockito.anyInt());
	}

	@Test
	public void atLeastOnceUsage() throws Exception {
		foo(list);
		verify(list, Mockito.atLeastOnce()).add(Mockito.anyInt());
	}

	@Test
	public void directlyArgumentCheckUsage() throws Exception {
		foo(list);
		verify(list, Mockito.atLeastOnce()).add(1);
	}

	@Test
	public void ArgumentCaptorUsage() throws Exception {
		foobar(list);
		ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
		verify(list).add(intCaptor.capture());
		assertThat(intCaptor.getValue() % 2, is(0));
	}

	@Test
	@Ignore
	public void ArgumentMatcherUsage() throws Exception {
		foobar(list);
		verify(list).add(Mockito.argThat(not(new IsEven())));
	}

	@Test
	public void inOrderUsage() throws Exception {
		InOrder inOrder = inOrder(list, list2);
		bar(list, list2);
		inOrder.verify(list).add(Mockito.anyInt());
		inOrder.verify(list2).add(Mockito.anyInt());
	}

	private final class IsEven extends ArgumentMatcher<Integer> {
		@Override
		public boolean matches(Object argument) {
			return ((Integer) argument) % 2 == 0;
		}
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
