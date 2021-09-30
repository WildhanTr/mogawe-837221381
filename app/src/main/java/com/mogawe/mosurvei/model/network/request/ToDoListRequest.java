package com.mogawe.mosurvei.model.network.request;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ToDoListRequest{

	@SerializedName("fieldTaskToDo")
	private List<FieldTaskToDoItem> fieldTaskToDo;

	public List<FieldTaskToDoItem> getFieldTaskToDo(){
		return fieldTaskToDo;
	}
}