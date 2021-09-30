package com.mogawe.mosurvei.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.network.response.sales.CatalogResponse;
import com.mogawe.mosurvei.model.repository.SalesRepository;

import java.util.List;

public class SalesViewModel extends AndroidViewModel {

    private SalesRepository salesRepository;
    private LiveData<CatalogResponse> responseLiveData;
    private LiveData<List<Catalog>> catalogLiveData;

    public SalesViewModel(@NonNull Application application) {
        super(application);
        salesRepository = new SalesRepository((MoSurveiApplication) application);
        responseLiveData = salesRepository.getResponseLiveData();

    }

    public void getCatalogList(String querySearch, String uuidCategorySelected, Integer page, Integer offset) {
        responseLiveData = salesRepository.getCatalogList(querySearch, uuidCategorySelected, page, offset);
    }

    public void getProdukList(String query, Integer page, Integer offset) {
        responseLiveData = salesRepository.getProdukList(query, page, offset);
    }

    public void getCategorySalesList() {
        responseLiveData = salesRepository.getCategorySalesList();
    }

    public void getFavoriteList(String query, Integer page, Integer offset) {
        responseLiveData = salesRepository.getFavoriteList(query, page, offset);
    }

    public void getOrderList(String query, String status, Integer page, Integer offset) {
        responseLiveData = salesRepository.getOrderList(query, status, page, offset);
    }

    public void getOrderListSales(String query, String status, Integer page, Integer offset) {
        responseLiveData = salesRepository.getOrderListSales(query, status, page, offset);
    }

    public void getDetailCatalog(String uuid) {
        responseLiveData = salesRepository.getDetailCatalog(uuid);
    }

    public void getDetailSummary(String uuid) {
        responseLiveData = salesRepository.getDetailSummary(uuid);
    }

    public void addToFavorite(String uuid) {
        responseLiveData = salesRepository.addToFavorite(uuid);
    }

    public void removeFromFavorite(String uuid) {
        responseLiveData = salesRepository.removeFromFavorite(uuid);
    }

    public void processOrder(String uuid) {
        responseLiveData = salesRepository.processOrder(uuid);
    }

    public void sentResi(String uuid, String shipmentResi) {
        responseLiveData = salesRepository.sentResi(uuid, shipmentResi);
    }

    public LiveData<CatalogResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public void setResponseLiveData(LiveData<CatalogResponse> responseLiveData) {
        this.responseLiveData = responseLiveData;
    }

    public LiveData<List<Catalog>> getCatalogLiveData() {
        return catalogLiveData;
    }

    public void setCatalogLiveData(LiveData<List<Catalog>> catalogLiveData) {
        this.catalogLiveData = catalogLiveData;
    }
}
