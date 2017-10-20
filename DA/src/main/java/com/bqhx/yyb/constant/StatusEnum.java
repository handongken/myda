package com.bqhx.yyb.constant;

public final class StatusEnum {
	enum sEnum {

		STATUS_0("0", "正常"), STATUS_1("1", "已赎回"), STATUS_2("2", "申请提前赎回");
		
		private String enumKey;
		private String enumValue;
		
		private sEnum(String enumKey,String enumValue) {
			this.enumKey = enumKey;
			this.enumValue = enumValue;
		}

		public String getEnumKey() {
			return enumKey;
		}

		public String getEnumValue() {
			return enumValue;
		}
	}

	public static String getValue(String enumKey){
		String value = null;
		if(enumKey != null){
			for(int i = 0;i < sEnum.values().length;i++){
				if(sEnum.values()[i].getEnumKey().equals(enumKey)){
					value = sEnum.values()[i].getEnumValue();
					return value;
				}
			}
		}
		return value;
	}
}
