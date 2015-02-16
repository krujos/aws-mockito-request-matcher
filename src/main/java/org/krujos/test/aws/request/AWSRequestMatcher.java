package org.krujos.test.aws.request;

import static org.mockito.Matchers.argThat;

import java.util.function.Predicate;

import org.hamcrest.*;

/**
 * @stolen http://source.coveo.com/2014/10/01/java8-mockito/
 *
 * @param <T>
 *            which becomes your <whatever>Request object from the aws api. You
 *            can then compare against whatever your expected value is.
 * 
 */
public class AWSRequestMatcher<T> extends BaseMatcher<T> {

	private Predicate<T> matcher;

	public static <T> T awsRqst(Predicate<T> predicate) {
		return argThat(new AWSRequestMatcher<T>(predicate));
	}

	private AWSRequestMatcher(Predicate<T> matcher) {
		this.matcher = matcher;
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object item) {
		return matcher.test((T) item);
	}

	public void describeTo(Description arg0) {
		// TODO Auto-generated method stub
	}

}