package utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


public class DateTimeMatcher extends TypeSafeMatcher<LocalDateTime> {

	private LocalDateTime actual;
	
	public DateTimeMatcher(LocalDateTime actual) {
		this.actual = actual.truncatedTo(ChronoUnit.MINUTES);
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("date ")
        	.appendText(actual.toString());
	}

	@Override
	protected boolean matchesSafely(LocalDateTime item) {
		return actual.compareTo(item.truncatedTo(ChronoUnit.MINUTES)) == 0;
	}
	
	public static DateTimeMatcher dateEquals(LocalDateTime date) {
		return new DateTimeMatcher(date);
	}
}