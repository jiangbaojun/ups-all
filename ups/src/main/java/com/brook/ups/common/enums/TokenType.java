package com.brook.ups.common.enums;

/**
 * token类型
 */
public enum TokenType {
    /**内部*/
	INNER("inner"),
    /**API*/
    API("api");
    /**成功*/

	private final String value;

	TokenType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static TokenType getTypeByValue(String val){
	    for(TokenType type:TokenType.values()){
	        if(type.getValue().equals(val)){
	            return type;
            }
        }
        return null;
    }
}
