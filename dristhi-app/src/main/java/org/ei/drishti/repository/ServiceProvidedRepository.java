package org.ei.drishti.repository;

import android.content.ContentValues;
import android.database.Cursor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.guardianproject.database.sqlcipher.SQLiteDatabase;
import org.ei.drishti.domain.ServiceProvided;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServiceProvidedRepository extends DrishtiRepository {

    private static final String SERVICE_PROVIDED_SQL = "CREATE TABLE service_provided(id VARCHAR, entityId VARCHAR, name VARCHAR, date VARCHAR, data VARCHAR)";

    public static final String SERVICE_PROVIDED_TABLE_NAME = "service_provided";
    public static final String ID_COLUMN = "id";
    public static final String ENTITY_ID_COLUMN = "entityId";
    public static final String NAME_ID_COLUMN = "name";
    public static final String DATE_ID_COLUMN = "date";
    public static final String DATA_ID_COLUMN = "data";

    public static final String[] SERVICE_PROVIDED_TABLE_COLUMNS = new String[]{ENTITY_ID_COLUMN, NAME_ID_COLUMN, DATE_ID_COLUMN, DATA_ID_COLUMN};


    public List<ServiceProvided> findByEntityIdAndName(String entityId, String[] names) {
        return null;
    }

    @Override
    protected void onCreate(SQLiteDatabase database) {
        database.execSQL(SERVICE_PROVIDED_SQL);
    }

    public void add(ServiceProvided serviceProvided) {
        SQLiteDatabase database = masterRepository.getWritableDatabase();
        database.insert(SERVICE_PROVIDED_TABLE_NAME, null, createValuesFor(serviceProvided));
    }

    public List<ServiceProvided> all() {
        SQLiteDatabase database = masterRepository.getReadableDatabase();
        Cursor cursor = database.query(SERVICE_PROVIDED_TABLE_NAME, SERVICE_PROVIDED_TABLE_COLUMNS, null, null, null, null, DATE_ID_COLUMN);
        return readAllServicesProvided(cursor);

    }

    private ContentValues createValuesFor(ServiceProvided serviceProvided) {
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN, UUID.randomUUID().toString());
        values.put(ENTITY_ID_COLUMN, serviceProvided.entityId());
        values.put(NAME_ID_COLUMN, serviceProvided.name());
        values.put(DATE_ID_COLUMN, serviceProvided.date());
        values.put(DATA_ID_COLUMN, new Gson().toJson(serviceProvided.data()));
        return values;
    }

    private List<ServiceProvided> readAllServicesProvided(Cursor cursor) {
        cursor.moveToFirst();
        List<ServiceProvided> servicesProvided = new ArrayList<ServiceProvided>();
        while (!cursor.isAfterLast()) {
            ServiceProvided serviceProvided = new ServiceProvided(
                    cursor.getString(cursor.getColumnIndex(ENTITY_ID_COLUMN)),
                    cursor.getString(cursor.getColumnIndex(NAME_ID_COLUMN)),
                    cursor.getString(cursor.getColumnIndex(DATE_ID_COLUMN)),
                    new Gson().<Map<String, String>>fromJson(cursor.getString(cursor.getColumnIndex(DATA_ID_COLUMN)), new TypeToken<Map<String, String>>() {
                    }.getType()));
            servicesProvided.add(serviceProvided);
            cursor.moveToNext();
        }
        cursor.close();
        return servicesProvided;
    }
}