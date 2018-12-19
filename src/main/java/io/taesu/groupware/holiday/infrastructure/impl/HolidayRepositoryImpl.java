package io.taesu.groupware.holiday.infrastructure.impl;

import io.taesu.groupware.common.util.DateUtils;
import io.taesu.groupware.holiday.infrastructure.HolidayRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-19
 */
public class HolidayRepositoryImpl implements HolidayRepository {
	/**
	 * Holiday 크롤링을 통해 수집된 공휴일 데이터를 미리 Load
	 * 추후 MST_HOLIDAY의 API 연동 필요
	 */
	private static final List<LocalDate> HOLIDAYS = Arrays.asList(
			LocalDate.of(2019, 1, 1),
			LocalDate.of(2019, 2, 4),
			LocalDate.of(2019, 2, 5),
			LocalDate.of(2019, 2, 6),
			LocalDate.of(2019, 3, 1),
			LocalDate.of(2019, 5, 5),
			LocalDate.of(2019, 5, 6),
			LocalDate.of(2019, 5, 12),
			LocalDate.of(2019, 6, 6),
			LocalDate.of(2019, 8, 15),
			LocalDate.of(2019, 9, 12),
			LocalDate.of(2019, 9, 13),
			LocalDate.of(2019, 9, 14),
			LocalDate.of(2019, 10, 3),
			LocalDate.of(2019, 10, 9),
			LocalDate.of(2019, 12, 25)
	);
	
	@Override
	public List<LocalDate> getHolidaysBetween(LocalDate startAt, LocalDate endAt) {
		return LongStream.rangeClosed(0, DateUtils.getAllDaysCount(startAt, endAt))
				.mapToObj(startAt::plusDays)
				.filter(HOLIDAYS::contains)
				.collect(Collectors.toList());
	}
}
