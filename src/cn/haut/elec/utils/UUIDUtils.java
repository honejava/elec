package cn.haut.elec.utils;

import java.util.UUID;

public class UUIDUtils {

	public static String getUUID() {
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	public static void main(String[] args) {
		System.out.println(getUUID());
	}

}
