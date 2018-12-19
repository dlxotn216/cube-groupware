package io.taesu.groupware.common.util;

import io.taesu.groupware.holiday.infrastructure.impl.HolidayRepositoryImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-12-19.
 */
public class DateUtilsTest {
	
	@Test
	public void 워킹데이_계산_테스트() {
		assertThat(DateUtils.getWorkingDaysCount(LocalDate.of(2019, 5, 5),
				LocalDate.of(2019, 12, 25),
				new HolidayRepositoryImpl())).isEqualTo(160);
		
		
		System.out.println(DateUtils.getWorkingDays(LocalDate.of(2019, 5, 5),
				LocalDate.of(2019, 12, 25),
				new HolidayRepositoryImpl()).stream()
				.map(LocalDate::toString).collect(Collectors.joining(", ")));
	}
	
}