package com.mogawe.mosurvei.model.bean.gawean;

import java.io.Serializable;

public class GaweanListResponseItem implements Serializable {
	private double jobFee;
	private String pictureUrl;
	private String name;
	private String description;
	private int gaweanCount;
	private String locationTrip;

	public double getJobFee(){
		return jobFee;
	}

	public String getPictureUrl(){
		return pictureUrl;
	}

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}

	public int getGaweanCount(){
		return gaweanCount;
	}

	public String getLocationTrip(){
		return locationTrip;
	}
}
