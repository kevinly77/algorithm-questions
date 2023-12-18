package interview.cake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MergingMeetingTimes {

	public static class Meeting {
		private int startTime;
		private int endTime;

		public Meeting(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public int getStartTime() {
			return startTime;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getEndTime() {
			return endTime;
		}

		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}

		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (!(o instanceof Meeting)) {
				return false;
			}
			final Meeting meeting = (Meeting) o;
			return startTime == meeting.startTime && endTime == meeting.endTime;
		}
	}

	public static List<Meeting> mergeRanges(List<Meeting> meetings) {

		List<Meeting> sortedMeetings = new ArrayList<>();
		List<Meeting> mergedMeetings = new ArrayList<>();

		// make a copy for each meeting and transfer over to sortedMeetings array
		for (Meeting meeting : meetings) {
			Meeting meetingCopy = new Meeting(meeting.getStartTime(), meeting.getEndTime());
			sortedMeetings.add(meetingCopy);
		}
		// sort meetings by start time
		sortedMeetings.sort(Comparator.comparing(Meeting::getStartTime));

		// add the first sorted meeting to merged meetings
		mergedMeetings.add(sortedMeetings.get(0));

		for (Meeting currentMeeting : sortedMeetings) {
			// get last merged meeting
			Meeting lastMergedMeeting = mergedMeetings.get(mergedMeetings.size() - 1);
			// compare end time of last merged to the current meeting start time
			if (lastMergedMeeting.getEndTime() >= currentMeeting.getStartTime()) {
				// if true, set the end time of last merged meeting to the greater of the two
				// end times
				lastMergedMeeting.setEndTime(Math.max(lastMergedMeeting.getEndTime(), currentMeeting.getEndTime()));
			} else {
				// we cannot merge the meetings, just add and move on
				mergedMeetings.add(currentMeeting);
			}
		}
		return mergedMeetings;

	}

	public static List<Meeting> mergeRangess(List<Meeting> meetings) {

		List<Meeting> sortedMeetings = new ArrayList<>();
		List<Meeting> finalMeetings = new ArrayList<>();
		for (Meeting meeting : meetings) {
			Meeting meetingCopy = new Meeting(meeting.getStartTime(), meeting.getEndTime());
			sortedMeetings.add(meetingCopy);
		}
		sortedMeetings.sort(Comparator.comparing(Meeting::getStartTime));
		finalMeetings.add(sortedMeetings.get(0));

		for (Meeting currentMeeting : sortedMeetings) {
			Meeting lastMergedMeeting = finalMeetings.get(finalMeetings.size() - 1);
			if (currentMeeting.getStartTime() <= lastMergedMeeting.getEndTime()) {
				lastMergedMeeting.setEndTime(Math.max(currentMeeting.getEndTime(), lastMergedMeeting.getEndTime()));
			} else {
				finalMeetings.add(currentMeeting);
			}
			for (Meeting m : finalMeetings) {
				System.out.println(m.getStartTime() + " " + m.getEndTime());
			}
		}
		return finalMeetings;
	}

	// tests

	@Test
	public void meetingsOverlapTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
		assertEquals(expected, actual);
	}

	@Test
	public void meetingsTouchTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(5, 6), new Meeting(6, 8));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(5, 8));
		assertEquals(expected, actual);
	}

	@Test
	public void meetingContainsOtherMeetingTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
		assertEquals(expected, actual);
	}

	@Test
	public void meetingsStaySeparateTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
		assertEquals(expected, actual);
	}

	@Test
	public void multipleMergedMeetingsTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(1, 4), new Meeting(2, 5), new Meeting(5, 8));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
		assertEquals(expected, actual);
	}

	@Test
	public void meetingsNotSortedTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(5, 8), new Meeting(1, 4), new Meeting(6, 8));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(1, 4), new Meeting(5, 8));
		assertEquals(expected, actual);
	}

	@Test
	public void oneLongMeetingContainsSmallerMeetingsTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(1, 10), new Meeting(2, 5), new Meeting(6, 8),
				new Meeting(9, 10), new Meeting(10, 12));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(1, 12));
		assertEquals(expected, actual);
	}

	@Test
	public void sampleInputTest() {
		final List<Meeting> meetings = Arrays.asList(new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8),
				new Meeting(10, 12), new Meeting(9, 10));
		final List<Meeting> actual = mergeRanges(meetings);
		final List<Meeting> expected = Arrays.asList(new Meeting(0, 1), new Meeting(3, 8), new Meeting(9, 12));
		assertEquals(expected, actual);
	}

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(MergingMeetingTimes.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests passed.");
		}

	}
}
