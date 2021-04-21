package de.oop.projekt.main;

import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable {

	private int day;
	private int month;
	private int year;

	public Date(int day, int month, int year) {
		setYear(year);
		setMonth(month);
		setDay(day);

	}
	
	@Override
	public String toString() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Date)) return false;
		Date date = (Date) o;
		return getDay() == date.getDay() && getMonth() == date.getMonth() && getYear() == date.getYear();
	}



	@Override
	public int hashCode() {
		return Objects.hash(getDay(), getMonth(), getYear());
	}

	public Date setDay(int day) {
		if (day < 1) {
			this.day = 1;
		} else if (day > 31) {
			if (isLongMonth()) {
				this.day = 31;
			} else if (this.getMonth() != 2) {
				this.day = 30;
			} else {
				if (this.isLeapYear()) {
					this.day = 29;
				} else {
					this.day = 28;
				}
			}
		} else if (day > 28 && this.getMonth() == 2 && !isLeapYear()) {
			this.day = 28;
		} else if (day > 29 && this.getMonth() == 2 && isLeapYear()) {
			this.day = 29;
		} else {
			this.day = day;
		}
		return this;
	}

	public Date setMonth(int month) {
		if (month < 1) {
			this.month = 1;
		} else if (month > 12) {
			this.month = 12;
		} else {
			this.month = month;
		}
		return this;
	}

	public Date setYear(int year) {
		this.year = year;
		return this;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public boolean isLeapYear() {

		if (this.getYear() % 4 == 0) {
			if (this.getYear() % 100 == 0) {
				if (this.getYear() % 400 == 0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean isUltimo() {
		if (this.getMonth() == 2) {
			if (isLeapYear()) {
				if (this.getDay() == 29) {
					return true;
				}
			}else {
				if (this.getDay() == 28) {
					return true;
				}
			}
		}

		if (isLongMonth()) {
			if (this.getDay() == 31)
				return true;
		} else {
			return this.getDay() == 30;
		}

		return false;

	}

	public boolean isLongMonth() {
		if (this.getMonth() <= 7) {
			return this.getMonth() % 2 == 1;
		} else {
			return this.getMonth() % 2 == 0;
		}
	}

	



	




}
