package com.mogawe.mosurvei.presenter.menu;

import com.mogawe.mosurvei.model.db.entity.HistoryResult;
import com.mogawe.mosurvei.model.network.request.Result.ResultHistoryRequest;
import com.mogawe.mosurvei.model.network.response.HistoryResultResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mobiletech on 5/7/17.
 */

public class HistoryPresenter {

    public void getHistoryResult(ResultHistoryRequest resultHistoryRequest) {
        List<HistoryResult> historyResults = null;
    }

    public void getHistoryResultV2(ResultHistoryRequest resultHistoryRequest) {

        String periode = null;
        String status = null;

        switch (resultHistoryRequest.getPeriode()) {
            case "Semua Hari":
                periode = "all";
                break;
            case "Seminggu terakhir":
                periode = "1-week";
                break;
            case "Sebulan terakhir":
                periode = "1-month";
                break;
            case "3 bulan terakhir":
                periode = "3-month";
                break;
        }

        if (resultHistoryRequest.getStatus().equals("Semua Status")) {
            status = null;
        } else {
            status = resultHistoryRequest.getStatus();
        }
    }
}
