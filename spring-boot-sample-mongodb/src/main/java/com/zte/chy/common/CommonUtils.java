package com.zte.chy.common;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class CommonUtils {
	public static Date getFormatDate(String timeStr) throws ParseException {
		if (StringUtils.isEmpty(timeStr))
			return null;
		return DateUtils.parseDate(timeStr, Constant.TIME_PARTTERNS);

	}

}
