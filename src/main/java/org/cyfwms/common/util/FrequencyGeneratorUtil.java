package org.cyfwms.common.util;

import org.springframework.stereotype.Component;

@Component
public class FrequencyGeneratorUtil {

    public int generateMonthlyUtil(int month, int cnt, int year, int counter) {
        boolean leapYear = false;
        if ((year % 4 == 0 && (year % 100 != 0)) || (year % 400 == 0)) {
            leapYear = true;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            cnt = cnt + 30;
        } else if (month == 2) {
            int daysInMonth = (leapYear) ? 29 : 28;
            cnt = cnt + daysInMonth;
        } else {
            cnt = cnt + counter;
        }
        return cnt;
    }

}
