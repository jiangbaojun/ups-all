package com.brook.ups.common.enums;

/**
 * 版本
 */
public enum Version {
    /** 内部版本 */
	INNER(0),
    /** 外部版本 */
    OUTER(1);

	private final Integer value;

	Version(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
