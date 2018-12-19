package io.taesu.groupware.holiday.infrastructure;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project groupware
 * @since 2018-12-19
 */
public interface HolidayRepository {
	List<LocalDate> getHolidaysBetween(LocalDate startAt, LocalDate endAt);
}
