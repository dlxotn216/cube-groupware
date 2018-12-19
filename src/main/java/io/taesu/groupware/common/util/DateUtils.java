package io.taesu.groupware.common.util;

import io.taesu.groupware.holiday.infrastructure.HolidayRepository;
import io.taesu.groupware.holiday.infrastructure.impl.HolidayRepositoryImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-19
 */
public final class DateUtils {
	
	private static final List<DayOfWeek> WEEKENDS = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
	
	public static Long getWorkingDaysCount(LocalDate startAt, LocalDate endAt, HolidayRepository holidayRepository) {
		List<LocalDate> holidays = holidayRepository.getHolidaysBetween(startAt, endAt);
		
		return LongStream.rangeClosed(0, getAllDaysCount(startAt, endAt))
				.mapToObj(startAt::plusDays)
				.filter(localDate -> !holidays.contains(localDate))
				.map(LocalDate::getDayOfWeek)
				.filter(dayOfWeek -> !WEEKENDS.contains(dayOfWeek))
				.count();
	}
	
	public static List<LocalDate> getWorkingDays(LocalDate startAt, LocalDate endAt, HolidayRepository holidayRepository) {
		List<LocalDate> holidays = holidayRepository.getHolidaysBetween(startAt, endAt);
		
		return LongStream.rangeClosed(0, getAllDaysCount(startAt, endAt))
				.mapToObj(startAt::plusDays)
				.filter(localDate -> !holidays.contains(localDate) && ! WEEKENDS.contains(localDate.getDayOfWeek()))
				.collect(Collectors.toList());
	}
	
	public static Long getAllDaysCount(LocalDate startAt, LocalDate endAt) {
		return IntStream.iterate(0, i -> i + 1)
				.limit(ChronoUnit.DAYS.between(startAt, endAt))
				.mapToObj(startAt::plusDays)
				.count();
	}
}
