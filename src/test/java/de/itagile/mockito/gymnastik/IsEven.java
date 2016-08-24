package de.itagile.mockito.gymnastik;

import org.mockito.ArgumentMatcher;

final class IsEven extends ArgumentMatcher<Integer> {
	public static IsEven isEven() {
		return new IsEven();
	}

	@Override
	public boolean matches(Object argument) {
		return ((Integer) argument) % 2 == 0;
	}
}
