package de.oop.projekt.main;

import java.io.*;

public class Address implements Serializable {
	
	String street;
	Integer houseNumber;
	String zipCode;
	String city;
	
	public Address(String s, Integer h, String p, String o) {
		street = s;
		houseNumber = h;
		zipCode = p;
		city = o;
	}

	@Override
	public String toString() {
		return "Wohnhaft:\n"+ street + houseNumber + "\n" + zipCode + city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equalsIgnoreCase(other.city))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equalsIgnoreCase(other.street))
			return false;
		return true;
	}
	
	
	
	

}
