package com.bqhx.yyb.constant;


public final class ContinueFlgEnum {
	enum cEnum {

		CONTINUEFLG_0("0", "续投"), CONTINUEFLG_1("1", "非续投");
		
		private String enumKey;
		private String enumValue;
		
		private cEnum(String enumKey,String enumValue) {
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
			for(int i = 0;i < cEnum.values().length;i++){
				if(cEnum.values()[i].getEnumKey().equals(enumKey)){
					value = cEnum.values()[i].getEnumValue();
					return value;
				}
			}
		}
		return value;
	}
}
