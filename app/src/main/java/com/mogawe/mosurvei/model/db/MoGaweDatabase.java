package com.mogawe.mosurvei.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mogawe.mosurvei.model.db.converter.CatalogImageConverter;
import com.mogawe.mosurvei.model.db.converter.CommissionsItemConverter;
import com.mogawe.mosurvei.model.db.converter.FactListConverter;
import com.mogawe.mosurvei.model.db.converter.ItemListConverter;
import com.mogawe.mosurvei.model.db.converter.JobActivitiesConverter;
import com.mogawe.mosurvei.model.db.converter.JobCertificatesConverter;
import com.mogawe.mosurvei.model.db.converter.JobTimeConverter;
import com.mogawe.mosurvei.model.db.converter.ResultListConverter;
import com.mogawe.mosurvei.model.db.converter.SectionListConverter;
import com.mogawe.mosurvei.model.db.converter.TimestampConverter;
import com.mogawe.mosurvei.model.db.dao.CatalogDao;
import com.mogawe.mosurvei.model.db.dao.DealsDao;
import com.mogawe.mosurvei.model.db.dao.NotificationDao;
import com.mogawe.mosurvei.model.db.dao.ResultDao;
import com.mogawe.mosurvei.model.db.dao.ResultListDao;
import com.mogawe.mosurvei.model.db.dao.SectionDao;
import com.mogawe.mosurvei.model.db.dao.SectionSourceDao;
import com.mogawe.mosurvei.model.db.dao.TaskDao;
import com.mogawe.mosurvei.model.db.dao.TaskPendingDao;
import com.mogawe.mosurvei.model.db.dao.TrackingDao;
import com.mogawe.mosurvei.model.db.dao.UserDao;
import com.mogawe.mosurvei.model.db.dao.WalletHistoryDao;
import com.mogawe.mosurvei.model.db.entity.Catalog;
import com.mogawe.mosurvei.model.db.entity.Deals;
import com.mogawe.mosurvei.model.db.entity.Notification;
import com.mogawe.mosurvei.model.db.entity.PendingTask;
import com.mogawe.mosurvei.model.db.entity.Result;
import com.mogawe.mosurvei.model.db.entity.ResultFact;
import com.mogawe.mosurvei.model.db.entity.ResultList;
import com.mogawe.mosurvei.model.db.entity.Section;
import com.mogawe.mosurvei.model.db.entity.Task;
import com.mogawe.mosurvei.model.db.entity.Tracking;
import com.mogawe.mosurvei.model.db.entity.User;
import com.mogawe.mosurvei.model.db.entity.WalletHistory;
import com.mogawe.mosurvei.model.network.response.section.SectionSourceResponse;

@Database(entities = {User.class, Tracking.class, Task.class, WalletHistory.class, Result.class, ResultFact.class, Deals.class, Section.class,
        SectionSourceResponse.class, PendingTask.class, ResultList.class, Notification.class, Catalog.class}, version = 109, exportSchema = false)
@TypeConverters({TimestampConverter.class, ItemListConverter.class, JobActivitiesConverter.class, JobTimeConverter.class, FactListConverter.class,
        ItemListConverter.class, SectionListConverter.class, ResultListConverter.class, CommissionsItemConverter.class, CatalogImageConverter.class, JobCertificatesConverter.class})
public abstract class MoGaweDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "MoDiBi";

    private static MoGaweDatabase instance;

    public static synchronized MoGaweDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MoGaweDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();

    public abstract TrackingDao trackingDao();

    public abstract TaskDao taskDao();

    public abstract WalletHistoryDao walletHistoryDao();

    public abstract ResultDao resultDao();

    public abstract DealsDao dealsDao();

    public abstract CatalogDao catalogDao();

    public abstract SectionDao sectionDao();

    public abstract SectionSourceDao sectionSourceDao();

    public abstract TaskPendingDao taskPendingDao();

    public abstract ResultListDao resultListDao();

    public abstract NotificationDao notificationDao();
}
