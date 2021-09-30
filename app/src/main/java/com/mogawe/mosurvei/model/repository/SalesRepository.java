package com.mogawe.mosurvei.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mogawe.mosurvei.MoSurveiApplication;
import com.mogawe.mosurvei.model.db.dao.CatalogDao;
import com.mogawe.mosurvei.model.db.dao.DealsDao;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.network.request.sales.Shipment;
import com.mogawe.mosurvei.model.network.response.point.DealsResponse;
import com.mogawe.mosurvei.model.network.response.sales.CatalogDetailResponse;
import com.mogawe.mosurvei.model.network.response.sales.CatalogResponse;
import com.mogawe.mosurvei.model.network.response.sales.CategoryProductResponse;
import com.mogawe.mosurvei.model.network.response.sales.DetailSummaryResponse;
import com.mogawe.mosurvei.model.network.response.sales.OrdersResponse;
import com.mogawe.mosurvei.model.store.PrefHelper;
import com.mogawe.mosurvei.model.store.PrefKey;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SalesRepository {

    private MoSurveiApplication application;
    private MutableLiveData<CatalogResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<Catalog>> catalogsLiveData;
    private CatalogDao catalogDao;

    public SalesRepository(MoSurveiApplication application) {
        this.application = application;
        if (application != null) {
            if (application.getDatabase() != null) {
                catalogDao = application.getDatabase().catalogDao();
                catalogsLiveData = catalogDao.findAll();
            }
        }
    }

    public MutableLiveData<CatalogResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getCatalogList(String querySearch, String uuidCategorySelected, Integer page, Integer offset) {
        application.getApiSalesService().getCatalogList(PrefHelper.getString(PrefKey.TOKEN), querySearch, uuidCategorySelected, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.GET_CATALOG);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getProdukList(String query, Integer page, Integer offset) {
        application.getApiSalesService().getProdukList(PrefHelper.getString(PrefKey.TOKEN), query, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.GET_PRODUK_LIST);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getCategorySalesList() {
        application.getApiSalesService().getCategorySalesList(PrefHelper.getString(PrefKey.TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryProductResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CategoryProductResponse response) {

                        CatalogResponse catalogResponse = new CatalogResponse();

                        if (response.isSuccess()) {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.GET_CATEGORY_PRODUCT);
                            catalogResponse.setCategoryProductResponse(response);

                        } else {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(catalogResponse);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getOrderList(String q, String status, int page, int offset) {

        if (status.equals("all") || status.equals(""))
            status = null;

        application.getApiSalesService().getOrderList(PrefHelper.getString(PrefKey.TOKEN), q, status, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(OrdersResponse response) {

                        CatalogResponse catalogResponse = new CatalogResponse();

                        if (response.isSuccess()) {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.GET_ORDER_LIST);
                            catalogResponse.setOrdersResponse(response);

                        } else {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(catalogResponse);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getOrderListSales(String q, String status, int page, int offset) {

        if (status.equals("all") || status.equals(""))
            status = null;

        application.getApiSalesService().getOrderListSales(PrefHelper.getString(PrefKey.TOKEN), q, status, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrdersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(OrdersResponse response) {

                        CatalogResponse catalogResponse = new CatalogResponse();

                        if (response.isSuccess()) {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.GET_ORDER_LIST);
                            catalogResponse.setOrdersResponse(response);

                        } else {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(catalogResponse);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getDetailCatalog(String uuid) {
        application.getApiSalesService().getDetailCatalog(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogDetailResponse response) {

                        CatalogResponse catalogResponse = new CatalogResponse();

                        if (response.isSuccess()) {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.GET_DETAIL_CATALOG);
                            catalogResponse.setCatalogDetailResponse(response);

                        } else {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(catalogResponse);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getDetailSummary(String uuid) {
        application.getApiSalesService().getDetailSummary(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailSummaryResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DetailSummaryResponse response) {

                        CatalogResponse catalogResponse = new CatalogResponse();

                        if (response.isSuccess()) {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.GET_DETAIL_SUMMARY);
                            catalogResponse.setDetailSummaryResponse(response);

                        } else {
                            catalogResponse.setReturnValue(response.getReturnValue());
                            catalogResponse.setMessage(response.getMessage());
                            catalogResponse.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(catalogResponse);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> getFavoriteList(String query, Integer page, Integer offset) {

        application.getApiSalesService().getFavoriteList(PrefHelper.getString(PrefKey.TOKEN), query, page, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.GET_FAVORITE_LIST);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> addToFavorite(String uuid) {
        application.getApiSalesService().addToFavorite(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.ADD_TO_FAVORITE);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> removeFromFavorite(String uuid) {
        application.getApiSalesService().removeFromFavorite(PrefHelper.getString(PrefKey.TOKEN), uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.REMOVE_FROM_FAVORITE);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> processOrder(String uuid) {
        Shipment shipment = new Shipment();
        shipment.setUuid(uuid);

        application.getApiSalesService().processOrder(PrefHelper.getString(PrefKey.TOKEN), shipment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.PROCESS_SUCCESS);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }

    public LiveData<CatalogResponse> sentResi(String uuid, String shipmentResi) {
        Shipment shipment = new Shipment();
        shipment.setUuid(uuid);
        shipment.setShipmentResi(shipmentResi);

        application.getApiSalesService().sentResi(PrefHelper.getString(PrefKey.TOKEN), shipment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CatalogResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(CatalogResponse response) {

                        if (response.isSuccess()) {
                            response.setRequestKey(CatalogResponse.RequestKey.SENT_RESI_SUCCESS);

                        } else {
                            response.setRequestKey(CatalogResponse.RequestKey.FAILED);

                        }
                        responseLiveData.setValue(response);
                    }
                });
        return responseLiveData;
    }
}
