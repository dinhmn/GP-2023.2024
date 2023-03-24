package com.graduationproject.backend.backendwebsiteshoe.Common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatetimeConvertFormat {

    /**
     * Get Date from numeric date value.
     *
     * @param year  year input
     * @param month month input
     * @param day   day input
     * @return Date
     */
    public static Date getDateByNumericInput(@NonNull final int year, @NonNull final int month, @NonNull final int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    /**
     * Convert Date to String date with format.
     *
     * @param pattern Datetime pattern
     * @param date    date
     * @return String date with format
     */
    public static String convertDateToStringWithFormat(@NonNull final String pattern, final Date date) {
        if (ObjectUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * Convert String to Date date with format.
     *
     * @param date    date
     * @return Date format
     */
    public static Date convertStringToDateWithFormat(final String date) {
        Calendar calendar = Calendar.getInstance();

        if (!(date.length() == 10 && date.length() == 9)) {
            return null;
        }
        String[] dates = date.split("-");
        calendar.set(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));

        return calendar.getTime();
    }

}
