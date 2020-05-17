package com.xyz.userservice.constants;

public class QueryConstants {

	public static class UserFields {
		public static final String USER_ID = "userId";
		public static final String EMAIL = "email";
		public static final String AMOUNT_TO_UPDATE = "amountToUpdate";
	}

	public static class UserQueries {
		public static final String UPDATE_USER_AVAILABLE_CREDITS = "updateUserAvailableCredits";
		public static final String UPDATE_USER_AVAILABLE_CREDITS_QRY = "UPDATE User set available_credits = available_credits + :amountToUpdate where id is :userId";
		public static final String GET_USER_BY_EMAIL = "getUserByEmail";
		public static final String GET_USER_BY_EMAIL_QRY = "FROM User where email is :email";
	}

}
