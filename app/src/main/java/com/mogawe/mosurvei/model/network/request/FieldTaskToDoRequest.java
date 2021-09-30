package com.mogawe.mosurvei.model.network.request;

import java.util.List;

public class FieldTaskToDoRequest extends GenericReq{

    public List<FieldTaskToDo> fieldTaskToDo;

    public List<FieldTaskToDo> getFieldTaskToDo() {
        return fieldTaskToDo;
    }

    public void setFieldTaskToDo(List<FieldTaskToDo> fieldTaskToDo) {
        this.fieldTaskToDo = fieldTaskToDo;
    }

    public static class FieldTaskToDo {
        public String uuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
