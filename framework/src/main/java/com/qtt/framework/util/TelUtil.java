/**
 *
 */
package com.qtt.framework.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bestar
 */
public class TelUtil {
    public class Call {
        String name;
        Long date;
        Long duration;
        int type;
        String phoneNumber;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the date
         */
        public Long getDate() {
            return date;
        }

        /**
         * @param date the date to set
         */
        public void setDate(Long date) {
            this.date = date;
        }

        /**
         * @return the duration
         */
        public Long getDuration() {
            return duration;
        }

        /**
         * @param duration the duration to set
         */
        public void setDuration(Long duration) {
            this.duration = duration;
        }

        /**
         * @return the type
         */
        public int getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(int type) {
            this.type = type;
        }

        /**
         * @return the phoneNumber
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * @param phoneNumber the phoneNumber to set
         */
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }


    }

    /**
     * 获取通话记录
     *
     * @param context
     * @return
     */
    public List<Call> getCallHistory(Context context) {
        List<Call> calls = new ArrayList<Call>();
        Call call = null;
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver()
                    .query(CallLog.Calls.CONTENT_URI, new String[]{CallLog.Calls.DATE, CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME},
                            null, null, CallLog.Calls.DATE + " desc limit 100");

            if (cursor.getCount() <= 0) {
                return null;
            }

            cursor.moveToFirst();
            do {
                call = new Call();
 
            /* Reading Date */
                call.setDate(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE)));

//            /* Reading duration */
//            call.setDuration(cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION)));

//            /* Reading Date */
//            call.setType(cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE)));;
// 
                call.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER)));
                ;
 
            /* Reading Name */
                String nameTemp = cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.CACHED_NAME));
                if (nameTemp == null) {
                    call.setName(getContactNameFromPhoneNum(context, call.getPhoneNumber()));
                } else {
                    call.setName(nameTemp);
                }
                calls.add(call);
            } while (cursor.moveToNext());
        } catch (Exception e) {

        } finally {
            if(cursor != null) cursor.close();
        }

        return calls;
    }

    public static void getConnects(Context context) {
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            //get name
            int nameFiledColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);
            String contact = cursor.getString(nameFiledColumnIndex);

            String[] PHONES_PROJECTION = new String[]{"_id", "display_name", "data1", "data3"};//
            String contactId = cursor.getString(cursor.getColumnIndex(PhoneLookup._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            //name type ..
            while (phone.moveToNext()) {
                int i = phone.getInt(0);
                String str = phone.getString(1);
                str = phone.getString(2);
                str = phone.getString(3);
            }
            phone.close();
            //addr
            Cursor addrCur = cr.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                    new String[]{"_id", "data1", "data2", "data3"}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            while (addrCur.moveToNext()) {
                int i = addrCur.getInt(0);
                String str = addrCur.getString(1);
                str = addrCur.getString(2);
                str = addrCur.getString(3);
            }
            addrCur.close();

            //email
            Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[]{"_id", "data1", "data2", "data3"}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            while (emailCur.moveToNext()) {
                int i = emailCur.getInt(0);
                String str = emailCur.getString(1);
                str = emailCur.getString(2);
                str = emailCur.getString(3);
            }
            emailCur.close();

        }
        cursor.close();
    }

    /**
     * 通过电话号码获取姓名
     */
    public String getContactNameFromPhoneNum(Context context, String phoneNum) {
        String contactName = "";
        ContentResolver cr = context.getContentResolver();
        Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?", new String[]{phoneNum}, null);
        if (pCur.moveToFirst()) {
            contactName = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        }
        pCur.close();
        if (contactName == "") {
            contactName = "未知";
        }
        return contactName;
    }

}
