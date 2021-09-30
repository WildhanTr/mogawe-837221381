package com.mogawe.mosurvei.model.network.request.user;

import java.io.Serializable;

public class GetDownlineRequest implements Serializable {
    private int page;
    private int offset;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
