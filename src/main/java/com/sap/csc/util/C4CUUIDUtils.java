package com.sap.csc.util;

public class C4CUUIDUtils {

	public static String convertToStandardForm(String oDataFormUUID) {
		String uuid = oDataFormUUID;

		if (uuid.length() > 32) {
			return uuid.replace("-", "");
		}

		return uuid;
	}

	public static String convertToODataForm(String uuid) {

		if (uuid.length() == 32) {
			StringBuilder sb = new StringBuilder();

			sb.append(uuid.substring(0, 8)).append("-").append(uuid.substring(8, 12)).append("-")
					.append(uuid.substring(12, 16)).append("-").append(uuid.substring(16, 20)).append("-")
					.append(uuid.substring(20));

			return sb.toString();
		}

		return uuid;
	}
}
