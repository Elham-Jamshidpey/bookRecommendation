package com.github.elhamjamshidpey.bookRecommendation.data;
/*
@uthor by Elham
May 27, 2019
*/

public enum LikeStatus {
	
	LIKE("like",2),
	DISLIKE("dislike",-2),
	NOT_INTERESTED("not_interested",1);
	
    private final String key;
    private final Integer value;

    LikeStatus(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
	
}
