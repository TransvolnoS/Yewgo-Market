package com.java.servlet;

import java.util.UUID;

public class UUIDCls {
	public static String getId() {
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString().replace("-", "").toUpperCase();
	}
}