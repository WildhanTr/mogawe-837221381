package com.mogawe.mosurvei.util;

/**
 * Created by glenrynaldi on 11/29/15.
 */
public class Constant {

    /**
     * PERMISSION REQUEST
     */
    public static final int REQ_PERMISSION_LOCATION = 0;
    public static final int REQ_PERMISSION_SMS = 1;

    public static final String FACT_TEXT = "1";
    public static final String FACT_TEXT_NUMERIC = "2";
    public static final String FACT_TEXT_DATE = "7";
    public static final String FACT_TEXT_TIME = "8";
    public static final String FACT_TEXT_EMAIL = "21";
    public static final String FACT_TEXT_PHONE = "22";
    public static final String FACT_TEXT_NAME = "23";
    public static final String FACT_SELECTION_TRUE_FALSE = "3";
    public static final String FACT_SELECTION_SINGLE = "4";
    public static final String FACT_SELECTION_MULTIPLE = "5";
    public static final String FACT_PICTURE_TAKE = "6";
    public static final String FACT_GEO_TAG = "9";
    public static final String FACT_MATRIX = "15";
    public static final String FACT_FORM_YOUGOV = "18";
    public static final String FACT_VIEW_IMAGE = "19";
    public static final String FACT_VIEW_IMAGE_SAMPOERNA = "20";
    public static final String FACT_TEXT_PASSWORD = "24";

    public static final Integer RESEND_TIME_MS = 60000;
    public static final Integer NEAR_JOB_NOTIFICATION_INTERVAL = 600000;
    public static final String SEARCH_GAWEAN = "searchgawean";
    public static final String ACTIVE_ORDER = "activegawean";
    public static final String SEARCH_ADVANCED = "searchadvanced";
    public static final String LIST_GAWEAN = "listgawean";

    //CONSTANT FRAGMENT
    public static final String CARI_GAWEAN_ADVANCED_FRAGMENT = "CariGaweanAdvancedFragment";
    public static final String CARI_GAWEAN_FRAGMENT = "CariGaweanFragment";
    public static final String CARI_GAWEAN_RESULT_FRAGMENT = "CariGaweanResultFragment";
    public static final String CARI_GAWEAN_ADVANCED_RESULT_FRAGMENT = "CariGaweanAdvancedResultFragment";
    public static final String CASH_OUT_FRAGMENT = "CashOutFragment";
    public static final String CASH_OUT_NOW_FRAGMENT = "CashOutNowFragment";
    public static final String GAWEAN_FRAGMENT = "GaweanFragment";
    public static final String PRODUCT_CATEGORY_FRAGMENT = "ProductCategoryFragment";
    public static final String GAWEAN_KU_FRAGMENT = "GaweanKuFragment";
    public static final String MAP_GAWEAN_KU_FRAGMENT = "MapGaweanKuFragment";
    public static final String GAWEAN_INFORMATION_FRAGMENT = "GaweanInformationFragment";
    public static final String HISTORY_FRAGMENT = "HistoryFragment";
    public static final String HISTORY_KU_FRAGMENT = "HistoryKuFragment";
    public static final String GAWEAN_PENDING_FRAGMENT = "GaweanPendingFragment";
    public static final String LOCATION_FRAGMENT = "LocationFragment";
    public static final String PHONE_ACTIVATION_FRAGMENT = "PhoneActivationFragment";
    public static final String PHONE_VALIDATION_FRAGMENT = "PhoneValidationFragment";
    public static final String PROFILE_FRAGMENT = "ProfileFragment";
    public static final String SECTION_FORM_FRAGMENT = "SectionFormFragment";
    public static final String INSTAGRAM_SECTION_FORM_FRAGMENT = "InstagramSectionFormFragment";
    public static final String WALLET_HISTORY_FRAGMENT = "WalletHistoryFragment";
    public static final String PENDING_PAYMENT_FRAGMENT = "PendingPaymentFragment";
    public static final String DEALS_FRAGMENT = "DealsFragment";
    public static final String MY_VOUCHER_FRAGMENT = "MyVoucherPaymentFragment";
    public static final String DEALS_REEDEM_FRAGMENT = "DealsReedemFragment";
    public static final String ROUTE_FRAGMENT = "RouteGaweanFragment";
    public static final String WORKING_FRAGMENT = "WorkingGaweanFragment";
    public static final String QC_FRAGMENT = "QCGaweanFragment";
    public static final String HISTORY_DETAIL_FRAGMENT = "HistoryDetailFragment";

    //CONSTANT ACTIVITIES
    public static final String HISTORY_ACTIVITY = "HistoryActivity";
    public static final String CARI_GAWEAN_ACTIVITY = "CariGaweanActivity";
    public static final String GAWEAN_INFORMATION_ACTIVITY = "GaweanInformationActivity";
    public static final String BANTUAN_ACTIVITY = "BantuanActivity";
    public static final String HOME_ACTIVITY = "HomeActivity";
    public static final String LIST_PRODUK_ACTIVITY = "ListProdukActivity";
    public static final String LIST_ORDER_ACTIVITY = "ListOrderActivity";
    public static final String HOME_SUPPLIER_ACTIVITY = "HomeSupplierActivity";

    //CONSTANT JOB ACTIVITES
    public static final String TAKE_PICTURE = "51aaffe1-9d74-4619-9551-f9b5a6d28a56";
    public static final String COUNTING = "a914f3a0-ba80-404a-9b2e-ec073091b212";
    public static final String COMMUNICATION = "a62cb113-f9cc-46f7-91ba-639b61332720";
    public static final String SHARING = "2394c7d6-1812-40e6-a0cc-6c1a82c990b5";

    //CONSTANT LOCATION LEVEL
    public static final String LEVEL_COUNTRY = "141f8bfb-4322-11e9-b2bb-42010a940029";
    public static final String LEVEL_PROVINCE = "a6d78ffc-5fee-4ba6-ae3d-fea039bcdb19";
    public static final String LEVEL_CITY = "1e16bd8a-0aaf-4e8b-af78-d33a97176195";
    public static final String LEVEL_DISTRICT = "ad73e6dd-e0e5-4187-9d52-bf2864345865";
    public static final String LEVEL_SUBDISTRICT = "3ece92d1-f93b-48ec-a224-077b7551077f";
    public static final String STREET = "e8c802ef-fb4b-4b10-ae4d-fd1eab896091";
    public static final String PLACE = "c4582989-4b90-46f1-9306-0c74de6906d6";

    //KATEGORI GAWEAN
    public static final String FEATURED = "FTR";
    public static final String GAWEANKU = "GWN";
    public static final String CARIGAWEAN = "SGW";
    public static final String WIDGET = "WID";
    public static final String ACTIVITIES = "ACT";
    public static final String STATISTIC = "STA";
    public static final String JOB_CATEGORY = "JCT";

    //VERSION STATE
    public static final String DEPRECATED_VERSION = "deprecate version";
    public static final String OLD_VERSION = "old version";
    public static final String LATEST_VERSION = "latest version";

    //LOGIN STATE
    public static final String PASSWORD_EMPTY = "Password tidak ditemukan";
    public static final String SOCMED_EMPTY_PASSWORD = "akun socmed tanpa password";

    public static final Integer HOME_CATEGORIES_LIMITER = 10;
    public static final Integer HOME_JOB_CATEGORIES_LIMITER = 10;
    public static final Integer GAWEAN_KU_LIMITER = 20;
    public static final Integer HISTORY_KU_LIMITER = 20;
    public static final Integer CHAT_KU_LIMITER = 20;
    public static final Integer LIST_CHAT_KU_LIMITER = 100;
    public static final Integer PAYMENT_HISTORY_LIMITER = 10;
    public static final Integer CATALOG_LIMITER = 30;

    //CONTENT TYPE
    public static final String CONTENT_JOB = "JOB";
    public static final String CONTENT_ADS = "BNR";
}